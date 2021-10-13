package com.cos.travel.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.travel.model.Blog;
import com.cos.travel.model.User;
import com.cos.travel.repository.BlogRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BlogService {

	private final BlogRepository blogRepository;
	
	//블로그 쓰기
	@Transactional
	public void insert(Blog blog, User user) {
		blog.setCount(0);
		blog.setUser(user);
		blogRepository.save(blog);
	}
	
	//블로그 리스트
	@Transactional(readOnly = true)
	public Page<Blog> list(Pageable pageable){
		return blogRepository.findAll(pageable);
	}
}
