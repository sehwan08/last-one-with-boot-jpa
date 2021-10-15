package com.cos.travel.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.travel.config.auth.PrincipalDetails;
import com.cos.travel.model.Blog;
import com.cos.travel.model.Reply;
import com.cos.travel.service.BlogService;
import com.cos.travel.web.dto.CMRespDto;
import com.cos.travel.web.dto.reply.ReplyDto;

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
	
	//블로그 수정
	@PutMapping("/api/blog/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody Blog blog){
		blogService.update(id, blog);
		return new ResponseEntity<>(new CMRespDto<>(1, "Success", null), HttpStatus.OK);
	}
	
	//댓글 쓰기
	@PostMapping("api/blogDetail/{blogId}/reply")
	public ResponseEntity<?> replySave(@RequestBody ReplyDto replyDto){
		blogService.replyInsert(replyDto);
		return new ResponseEntity<>(new CMRespDto<>(1, "Success", null), HttpStatus.OK);
	}
	
	//댓글 삭제
	@DeleteMapping("/api/blogDetail/{blogId}/reply/{replyId}")
	public ResponseEntity<?> replyDelete(@PathVariable int replyId){
		blogService.deleteReply(replyId);
		return new ResponseEntity<>(new CMRespDto<>(1, "Success", null), HttpStatus.OK);
	}
}
