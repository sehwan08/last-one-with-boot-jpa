package com.cos.travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.travel.model.Blog;
import com.cos.travel.model.Likes;
import com.cos.travel.repository.BlogRepository;
import com.cos.travel.repository.LikesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikesService {
	private final LikesRepository likesRepository;
	private final BlogRepository blogRepository;

	
	//좋아요
	@Transactional
	public void like(int blogId, int principalId) {
		Optional<Blog> blog = blogRepository.findById(blogId);
		if(blog.isPresent()) {
			System.out.println("좋아요 증가 로직탐?==================");
			Blog blog1 = blog.get();
			blog1.setLikeCount(blog1.getLikeCount()+1);
			blogRepository.save(blog1);
		}
		likesRepository.like(blogId, principalId);
	}

	//좋아요 취소
	@Transactional
	public void unlike(int blogId, int principalId) {
		Optional<Blog> blog = blogRepository.findById(blogId);
		if(blog.isPresent()) {
			Blog blog1 = blog.get();
			blog1.setLikeCount(blog1.getLikeCount()-1);
			blogRepository.save(blog1);
		}
		likesRepository.unlike(blogId, principalId);
	}
	
	//좋아요 서치
	@Transactional
	public List<Likes> likescheck(int blogId, int principalId) {
		return likesRepository.checklikes(blogId, principalId);
	}

}
