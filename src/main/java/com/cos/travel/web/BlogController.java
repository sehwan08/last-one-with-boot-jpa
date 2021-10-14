package com.cos.travel.web;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.travel.model.Blog;
import com.cos.travel.service.BlogService;

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

}
