package com.cos.travel.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.travel.model.Blog;
import com.cos.travel.service.BlogService;
import com.cos.travel.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BlogController {

	private final BlogService blogService;

	// 여행이야기 이동, 리스팅
	@GetMapping("/blog/blogMain")
	public String blog(Model model,
			@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Blog> lists = blogService.list(pageable);

		/*
		 * Pattern pattern = Pattern.compile( ".*<img src=\"(.*?)\" style.*" );
		 * 
		 * 
		 * for(int i = 0; i<lists.getSize(); i++) { String val = lists.get(i); //대상문자열
		 * Matcher matcher = pattern.matcher(val); System.out.println(matcher.find()); }
		 * 
		 * 
		 * 
		 * lists.forEach(item -> {
		 * 
		 * String val = item.getContent();
		 * 
		 * Matcher matcher = pattern.matcher(val); if(matcher.matches()) {
		 * System.out.println("---------------------------"); String match =
		 * matcher.group(1); System.out.println(match); //
		 * System.out.println(matcher.find()); // System.out.println(matcher.group(0));
		 * } else { item.setContent(""); }
		 * 
		 * });
		 */

		model.addAttribute("lists", lists);

		return "blog/blogMain";
	}

	// 블로그 쓰기
	@GetMapping("/blog/blogForm")
	public String blogForm() {
		return "blog/blogForm";
	}

}
