package com.cos.travel.web;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.travel.config.auth.PrincipalDetails;
import com.cos.travel.model.Blog;
import com.cos.travel.service.BlogService;
import com.cos.travel.web.dto.search.SearchDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BlogController {

	private final BlogService blogService;

	// 여행이야기 이동, 리스팅
	@GetMapping("/blog/blogMain")
	public String blog(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Blog> lists = blogService.list(pageable);
		model.addAttribute("lists", lists);
		return "blog/blogMain";
	}

	// 블로그 쓰기
	@GetMapping("/blog/blogForm")
	public String blogForm() {
		return "blog/blogForm";
	}
	
	// 블로그 상세 보기
	@GetMapping("/blog/blogDetail/{id}")
	public String findById(@PathVariable int id,
			Model model/* , @AuthenticationPrincipal PrincipalDetails principalDetails */) {
		
		//System.out.println("principal username:"+principalDetails.getUsername());
		
		String username = "";
		
		Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
		if (principal instanceof UserDetails) {
		username = ((UserDetails)principal). getUsername();
		} else {
		username = principal. toString();
		}
		//System.out.println("username:"+username);
		
		Blog blog = blogService.detail(id);
		//System.out.println("blog의 username:"+blog.getUser().getUsername());
		
		if (!username.equals(blog.getUser().getUsername())) {
			//System.out.println("if문 실행됨?=======================");
			blog.setCount(blog.getCount()+1);
		}
		
		blogService.update(id, blog);
		model.addAttribute("blog", blog);
		return "blog/blogDetail";
	}
	
	//블로그 수정 이동
	@GetMapping("/blog/blogUpdate/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("blog", blogService.view(id));
		return "blog/blogupdateForm";
	}
	
	//공지사항 삭제
	@DeleteMapping("/blog/blogUpdate/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		blogService.delete(id);
		return "success";
	}
	
	//검색
	@GetMapping("/blog/findbytext")
	public String findByText(Model model, @ModelAttribute SearchDto dto, @PageableDefault(size = 3, sort = "id",
	direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Blog> lists = blogService.searchByText(dto, pageable);
		model.addAttribute("lists", lists);
		System.out.println("개수 실행 됨?============"+lists.getTotalElements());
		System.out.println("아이디 뭐임?==========="+dto.getText());
		return "blog/blogMain";
	}

}
