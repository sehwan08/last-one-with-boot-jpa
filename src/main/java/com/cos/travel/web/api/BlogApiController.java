package com.cos.travel.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.travel.config.auth.PrincipalDetails;
import com.cos.travel.model.Blog;
import com.cos.travel.model.Board;
import com.cos.travel.service.BlogService;
import com.cos.travel.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
	
	private final BlogService blogService;
	
	//블로그 쓰기
	@PostMapping("blog/blogForm")
	public ResponseEntity<?> save(@RequestBody Blog blog,
			@AuthenticationPrincipal PrincipalDetails principal){
		blogService.insert(blog, principal.getUser());
		return new ResponseEntity<>(new CMRespDto<>(1, "Success", null), HttpStatus.OK);
	}
}
