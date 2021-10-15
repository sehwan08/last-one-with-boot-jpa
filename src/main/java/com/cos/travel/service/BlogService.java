package com.cos.travel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.travel.model.Blog;
import com.cos.travel.model.Board;
import com.cos.travel.model.Reply;
import com.cos.travel.model.User;
import com.cos.travel.repository.BlogRepository;
import com.cos.travel.repository.ReplyRepository;
import com.cos.travel.repository.UserRepository;
import com.cos.travel.web.dto.reply.ReplyDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BlogService {

	private final BlogRepository blogRepository;
	private final ReplyRepository replyRepository;
	private final UserRepository userRepository;

	// 블로그 쓰기
	@Transactional
	public void insert(Blog blog, User user) {
		blog.setCount(0);
		blog.setUser(user);
		blogRepository.save(blog);
	}

	// 블로그 리스트
	@Transactional(readOnly = true)
	public Page<Blog> list(Pageable pageable) {
		return blogRepository.findAll(pageable);
	}

	// 블로그 상세보기
	@Transactional(readOnly = true)
	public Blog detail(int id) {
		return blogRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("상세보기 실패");
		});
	}

	// 조회수
	@Transactional
	public Blog view(int id) {
		Blog blog = blogRepository.findById(id).get();
		return blog;
	}

	// 블로그 수정하기
	@Transactional
	public void update(int id, Blog requestBlog) {
		Blog blog = blogRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("공지사항 수정 실패");
		});
		blog.setTitle(requestBlog.getTitle());
		blog.setContent(requestBlog.getContent());
	}

	// 블로그 삭제
	@Transactional
	public void delete(int id) {
		blogRepository.deleteById(id);
	}

	// 댓글 쓰기
	@Transactional
	public void replyInsert(ReplyDto replyDto) {

		/* 영속화 첫번째 개념
		 * User user = userRepository.findById(replyDto.getUserId()).orElseThrow(() -> {
		 * return new IllegalArgumentException("댓글 실패 : 유저 아이디 못찾음"); }); // 영속화
		 * 
		 * Blog blog = blogRepository.findById(replyDto.getBlogId()).orElseThrow(() -> {
		 * return new IllegalArgumentException("댓글 실패 : 블로그 아이디 못찾음"); });
		 * 
		 * 
		 * Reply reply = new Reply(); reply.update(user, blog, replyDto.getContent());
		 * 
		 * 
		 * Reply reply =
		 * Reply.builder().user(user).blog(blog).content(replyDto.getContent()).build();
		 */
		
		replyRepository.mSave(replyDto.getUserId(), replyDto.getBlogId(), replyDto.getContent());
	}
	
	//댓글 삭제
	@Transactional
	public void deleteReply(int replyId) {
		replyRepository.deleteById(replyId);
	}
}
