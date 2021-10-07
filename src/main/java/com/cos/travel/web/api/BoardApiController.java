package com.cos.travel.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.travel.config.auth.PrincipalDetails;
import com.cos.travel.model.Board;
import com.cos.travel.service.BoardService;
import com.cos.travel.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
	
	private final BoardService boardService;
	
	//공지사항 쓰기
	@PostMapping("board/noticeForm")
	public ResponseEntity<?> save(@RequestBody Board board,
			@AuthenticationPrincipal PrincipalDetails principal){
		boardService.insert(board, principal.getUser());
		return new ResponseEntity<>(new CMRespDto<>(1, "Success", null), HttpStatus.OK);
	}

}
