package com.cos.travel.web;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.travel.model.User;
import com.cos.travel.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	
	private final UserService userService;
	
	//회원 관리 목록
	@GetMapping("/admin/userlist")
	public String userlist(Model model, @PageableDefault(size = 3, sort = "id",
	direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> lists = userService.list(pageable);
		model.addAttribute("lists", lists);
		return "admin/userlist";
	}
	
	//회원 삭제
	@DeleteMapping("/admin/delete/{id}")
	@ResponseBody
	public String userlist(@PathVariable int id) {
		userService.listuserdelete(id);
		return "success";
	}
}
