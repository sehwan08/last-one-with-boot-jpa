package com.cos.travel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.travel.model.Board;
import com.cos.travel.model.User;
import com.cos.travel.repository.BlogRepository;
import com.cos.travel.repository.BoardRepository;
import com.cos.travel.web.dto.search.SearchDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BlogRepository blogRepository;
	private final BoardRepository boardRepository;

	// 공지사항 쓰기
	@Transactional
	public void insert(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	// 공지사항 리스트
	@Transactional(readOnly = true)
	public Page<Board> list(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	// 공지사항 상세보기
	@Transactional(readOnly = true)
	public Board detail(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("상세보기 실패");
		});
	}

	// 조회수
	@Transactional
	public Board view(int id) {
		Board board = boardRepository.findById(id).get();
		return board;
	}

	// 공지사항 삭제
	@Transactional
	public void delete(int id) {
		boardRepository.deleteById(id);
	}

	// 글 수정하기
	@Transactional
	public void update(int id, Board requestBoard) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("공지사항 수정 실패");
		});
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
	}
	
	// 블로그삭제 삭제
	@Transactional
	public void deleteBlog(int id) {
		blogRepository.deleteById(id);
	}

	// 검색 - 모두
	@Transactional(readOnly = true)
	public Page<Board> searchByText(SearchDto dto, Pageable pageable) {

		Page<Board> boardlist = null;

		System.out.println("pageable.getOffset()=" + pageable.getOffset());
		System.out.println("pageable.getPageSize()=" + pageable.getPageSize());
		System.out.println("pageable.getPageNumber()=" + pageable.getPageNumber());

		switch (dto.getGubun()) {
		/*
		 * case "전체": System.out.println("======================="); userlist =
		 * boardRepository.findByText(dto.getText(), pageable); break;
		 */
			case "작성일":
				System.out.println("1111==========호출============");
				boardlist = boardRepository.searchByDate(dto.getText(), pageable);
				break;
			case "제목 내용":
				System.out.println("2222==========호출============");
				boardlist = boardRepository.findByText(dto.getText(), pageable);
				break;
		}
		return boardlist;
	}
}
