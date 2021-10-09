package com.cos.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.travel.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	public void deleteById(int id);
	
}