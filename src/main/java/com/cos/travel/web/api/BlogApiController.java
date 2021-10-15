package com.cos.travel.web.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.travel.config.auth.PrincipalDetails;
import com.cos.travel.model.Blog;
import com.cos.travel.model.Likes;
import com.cos.travel.repository.LikesRepository;
import com.cos.travel.service.BlogService;
import com.cos.travel.service.LikesService;
import com.cos.travel.web.dto.CMRespDto;
import com.cos.travel.web.dto.reply.ReplyDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

	private final BlogService blogService;
	private final LikesService likesService;

	// 블로그 쓰기
	@PostMapping("blog/blogForm")
	public ResponseEntity<?> save(@RequestBody Blog blog, @AuthenticationPrincipal PrincipalDetails principal) {
		blogService.insert(blog, principal.getUser());
		return new ResponseEntity<>(new CMRespDto<>(1, "Success", null), HttpStatus.OK);
	}

	// 블로그 수정
	@PutMapping("/api/blog/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody Blog blog) {
		blogService.update(id, blog);
		return new ResponseEntity<>(new CMRespDto<>(1, "Success", null), HttpStatus.OK);
	}

	// 댓글 쓰기
	@PostMapping("api/blogDetail/{blogId}/reply")
	public ResponseEntity<?> replySave(@RequestBody ReplyDto replyDto) {
		blogService.replyInsert(replyDto);
		return new ResponseEntity<>(new CMRespDto<>(1, "Success", null), HttpStatus.OK);
	}

	// 댓글 삭제
	@DeleteMapping("/api/blogDetail/{blogId}/reply/{replyId}")
	public ResponseEntity<?> replyDelete(@RequestBody ReplyDto replyDto) {
		
		System.out.println(replyDto.toString()+"==============================");
		
		blogService.deleteReply(replyDto);
		return new ResponseEntity<>(new CMRespDto<>(1, "Success", null), HttpStatus.OK);
	}

	// 좋아요
	@PostMapping("/api/blog/{blogId}/likes")
	public ResponseEntity<?> likes(@PathVariable int blogId,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		likesService.like(blogId, principalDetails.getUser().getId());
		return new ResponseEntity<>(new CMRespDto<>(1, "success", null), HttpStatus.CREATED);
	}

	// 좋아요 취소
	@DeleteMapping("/api/blog/{blogId}/likes")
	public ResponseEntity<?> unlikes(@PathVariable int blogId,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		likesService.unlike(blogId, principalDetails.getUser().getId());
		return new ResponseEntity<>(new CMRespDto<>(1, "success", null), HttpStatus.CREATED);
	}
	
	// 좋아요 조회
	@GetMapping("/api/blog/{blogId}/likes")
	public ResponseEntity<?> checklikes(@PathVariable int blogId,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		List<Likes> likesList = likesService.likescheck(blogId, principalDetails.getUser().getId());
		if (likesList.size() == 0) {
			return new ResponseEntity<>(new CMRespDto<>(1, "no", null), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(new CMRespDto<>(1, "yes", null), HttpStatus.CREATED);
		}
	}
}
