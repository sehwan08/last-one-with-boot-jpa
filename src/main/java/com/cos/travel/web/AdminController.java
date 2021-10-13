package com.cos.travel.web;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.travel.model.User;
import com.cos.travel.service.BoardService;
import com.cos.travel.service.UserService;
import com.cos.travel.web.dto.search.SearchDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	
	private final UserService userService;
	private final BoardService boardService;
	
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
	
	//공지사항 삭제
	@DeleteMapping("/board/notice/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		boardService.delete(id);
		return "success";
	}
	
	//공지사항 수정 이동
	@GetMapping("/board/noticeupdate/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.view(id));
		return "board/noticeupdateForm";
	}
	
	/*
	 * //회원 아이디로 검색
	 * 
	 * @GetMapping("/admin/searchUsername/{username}") public String
	 * findUsername(Model model, @PageableDefault(size = 3, sort = "id", direction =
	 * Sort.Direction.DESC) Pageable pageable, @PathVariable String username) {
	 * 
	 * System.out.println(username);
	 * 
	 * Page<User> lists = userService.searchUsername(username, pageable);
	 * model.addAttribute("lists", lists); return "admin/userlist"; }
	 * 
	 * //회원 메일 검색
	 * 
	 * @GetMapping("/admin/searchEmail/{email}") public String findEmail(Model
	 * model, @PageableDefault(size = 3, sort = "id", direction =
	 * Sort.Direction.DESC) Pageable pageable, @PathVariable String email) {
	 * 
	 * System.out.println(email);
	 * 
	 * Page<User> lists = userService.searchEmail(email, pageable);
	 * model.addAttribute("lists", lists); return "admin/userlist"; }
	 */
	
	//모든 검색 컨트롤러
	@GetMapping("/admin/findbytext")
	public String findByText(Model model, @ModelAttribute SearchDto dto, @PageableDefault(size = 3, sort = "id",
	direction = Sort.Direction.DESC) Pageable pageable) {
		
//		System.out.println("===========호출==============");
//		System.out.println(dto.getText()+" "+dto.getGubun());
		Page<User> lists = userService.searchByText(dto, pageable);
		model.addAttribute("lists", lists);
		return "admin/userlist";
	}
}
