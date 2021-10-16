package com.cos.travel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.travel.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
	
	public void deleteById(int id);
	
	//아이디 검색
	@Query(value = "SELECT * FROM Blog WHERE " + "writer LIKE %?1% ",
			countQuery = "SELECT count(*) FROM Blog WHERE writer LIKE %?1%",
			nativeQuery = true)
	Page<Blog> searchByName(String writer, Pageable pageable);
	
	//날짜 검색
	@Query(value = "SELECT * from Blog WHERE date_format(blog.createDate, '%Y-%m-%d') LIKE %?1%",
			countQuery = "SELECT count(*) FROM Blog WHERE date_format(blog.createDate, '%Y-%m-%d') LIKE %?1%",
			nativeQuery = true)
	Page<Blog> searchByDate(String date, Pageable pageable);
	
	
	
	//제목+타이틀
	@Query(value = "SELECT * FROM Blog WHERE " + "title LIKE %?1% or " + "content LIKE %?1% ",
			countQuery = "SELECT count(*) FROM Blog WHERE title LIKE %?1% or content LIKE %?1%",
		    nativeQuery = true)
	Page<Blog> findByText(String searchText, Pageable pageable);
	
	
	//아이디 검색(유저 모델 쓰기)
	/*
	 * @Query(value =
	 * "SELECT b.* FROM Blog  b WHERE  b.userId IN (select id from User u where u.username like '%?1%') "
	 * , countQuery =
	 * "SELECT count(b.*) FROM Blog  b WHERE  b.userId IN (select id from User u where u.username like '%?1%') "
	 * , nativeQuery = true) Page<Blog> searchByUsername(String username, Pageable
	 * pageable);
	 */
}

