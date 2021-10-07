package com.cos.travel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BoardController {
	
	
	
	//공지사항 이동
	@GetMapping("/board/notice")
	public String notice() {
		return "board/notice";
	}
	
	//공지사항 쓰기 이동
	@GetMapping("/board/noticeForm")
	public String noticeForm() {
		return "board/noticeForm";
	}

}
