package com.cos.travel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
	
	//여행이야기 이동
	@GetMapping("/blog/blogMain")
	public String blog() {
		return "blog/blogMain";
	}
	
	//블로그 쓰기
	@GetMapping("/blog/blogForm")
	public String blogForm() {
		return "blog/blogForm";
	}

}
