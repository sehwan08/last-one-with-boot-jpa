package com.cos.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.travel.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	public void deleteById(int id);
	
	
}
