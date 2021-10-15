package com.cos.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.travel.model.Likes;

public interface LikesRepository extends JpaRepository<Likes, Integer> {

	@Modifying
	@Query(value = "INSERT INTO likes(blogId, userId, createDate) VALUES (:blogId, :principalId, now())", nativeQuery = true)
	int like(int blogId, int principalId);

	@Modifying
	@Query(value = "Delete FROM likes WHERE blogId = :blogId AND userId = :principalId", nativeQuery = true)
	int unlike(int blogId, int principalId);
	
	@Modifying
	@Query(value = "SELECT * FROM likes WHERE blogId = :blogId AND userId = :principalId", nativeQuery = true)
	List<Likes> checklikes(int blogId, int principalId);
	
}
