package com.cos.travel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.travel.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	public void deleteById(int id);
	
	
	//검색
	@Query(value = "SELECT * from Board WHERE date_format(board.createDate, '%Y-%m-%d') LIKE %?1%",
			countQuery = "SELECT count(*) FROM Board WHERE date_format(board.createDate, '%Y-%m-%d') LIKE %?1%",
			nativeQuery = true)
	Page<Board> searchByDate(String date, Pageable pageable);
	
	
	Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
	
	
	//제목/타이틀
	@Query(value = "SELECT * FROM Board WHERE " + "title LIKE %?1% or " + "content LIKE %?1% ",
			countQuery = "SELECT count(*) FROM Board WHERE title LIKE %?1% or content LIKE %?1%",
		    nativeQuery = true)
	Page<Board> findByText(String searchText, Pageable pageable);
	
	
}
  