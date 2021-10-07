package com.cos.travel.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.travel.config.auth.PrincipalDetails;
import com.cos.travel.handler.ex.CustomUpdateValidationException;
import com.cos.travel.model.User;
import com.cos.travel.service.UserService;
import com.cos.travel.web.dto.CMRespDto;
import com.cos.travel.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {

	private final UserService userService;

	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(@PathVariable int id, @Valid UserUpdateDto dto, BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {

		System.out.println(dto);
		
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomUpdateValidationException("Failed", errorMap);
		} else {
			User userEntity = userService.update(id, dto.toEntity());
			principalDetails.setUser(userEntity);
			return new CMRespDto<>(1, "업데이트 성공", userEntity);
		}
	}
}
