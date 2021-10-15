package com.cos.travel.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.travel.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	
	@Modifying
	@Query(value = "INSERT INTO reply (userId,  blogId, content, createDate) VALUES(?1,?2,?3, now())", nativeQuery = true)
	int mSave(int userId, int blogId, String content);
	//modifying은 리턴값이 숫자형이기때문에 int로 받아야 함
}
 