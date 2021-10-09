package com.cos.travel.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.travel.config.auth.PrincipalDetails;
import com.cos.travel.handler.ex.CustomValidationException;
import com.cos.travel.model.User;
import com.cos.travel.repository.UserRepository;
import com.cos.travel.service.UserService;
import com.cos.travel.web.dto.CMRespDto;
import com.cos.travel.web.dto.user.JoinDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;

	@GetMapping("/login")
	public String loginForm() {
		return "user/login";
	}
	
	@GetMapping("/join")
	public String joinForm() {
		return "user/join";
	}
	
	@PostMapping("/join")
	public String join(@Valid JoinDto dto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("====================");
				System.out.println(error.getDefaultMessage());
			}
			throw new CustomValidationException("실패!", errorMap);
			
		} else {
			
			User user = dto.toEntity();
			User userEntity = userService.join(user);
			System.out.println(userEntity);
			return "user/login";
		}
	}
	
	//중복확인
	@PostMapping("/idCheck")
	@ResponseBody
	public String idCheck(@RequestBody User user) {
		
		boolean result = userService.idCheck(user);
		
		if( result == false ) {
			return "Fail";
		}
		return "success";
	}
	
	
	//업데이트 폼 이동
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		return "user/update";
	}
	
	//삭제
	@DeleteMapping("/user/{id}/delete")
	@ResponseBody
	public String delete(@PathVariable int id){
		userService.delete(id);
		return "success";
	}
	
}
