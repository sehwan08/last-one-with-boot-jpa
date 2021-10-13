package com.cos.travel.service;



import java.util.List;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cos.travel.model.User;
import com.cos.travel.repository.UserRepository;
import com.cos.travel.web.dto.search.SearchDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	//회원 가입 진행
	@Transactional
	public User join(User user) {
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");
		User userEntity = userRepository.save(user);
		return userEntity;
	}
	
	
	//회원 수정 진행
	@Transactional
	public User update(int id, User user) {
		User userEntity = userRepository.findById(id).get();
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		userEntity.setPassword(encPassword);
		userEntity.setEmail(user.getEmail());
		userEntity.setAge(user.getAge());
		userEntity.setGender(user.getGender());
		userEntity.setPreference(user.getPreference());
		
		return userEntity;
	}
	
	//회원 탈퇴
	@Transactional
	public void delete(int id) {
//		System.out.println("=======나 실행됨?======"+ id);
		userRepository.deleteById(id);
	}
	
	//회원 목록 들고 오기
	@Transactional(readOnly = true)
	public Page<User> list(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	
	//회원 삭제
	@Transactional
	public void listuserdelete(int id){
		userRepository.deleteById(id);
	}
	
	//회원 중복 확인
	@Transactional(readOnly = true)
	public boolean idCheck(User user) {
		if(userRepository.findByUsername(user.getUsername()) != null)
			return false;
		return true;
	}
	
	//회원 이메일 확인
	@Transactional(readOnly = true)
	public boolean emailCheck(User user) {
		if(userRepository.findByEmail(user.getEmail()) != null)
			return false;
		return true;
	}
	
	/*
	 * //검색 - 아이디
	 * 
	 * @Transactional(readOnly = true) public Page<User> searchUsername(String
	 * username, Pageable pageable){ return
	 * userRepository.findByUsernameContaining(username, pageable); }
	 * 
	 * //검색 - 이메일
	 * 
	 * @Transactional(readOnly = true) public Page<User> searchEmail(String email,
	 * Pageable pageable){ return userRepository.findByEmailContaining(email,
	 * pageable); }
	 */
	
	//검색 - 모두
	@Transactional(readOnly = true)
	public Page<User> searchByText(SearchDto dto, Pageable pageable){
		
		Page<User> userlist = null;
		
		System.out.println("pageable.getOffset()="+pageable.getOffset());
		System.out.println("pageable.getPageSize()="+pageable.getPageSize());
		System.out.println("pageable.getPageNumber()="+pageable.getPageNumber());
		
		switch (dto.getGubun()) {
			case "모두":
				/*
				 * List<User> list = userRepository.findByText(dto.getText()); int start = (int)
				 * (pageable.getOffset()); int end = (int) (start + pageable.getPageSize()); if(
				 * end > list.size() ) { end = list.size(); } // Do your process to get output
				 * in a list by using node.js run on a *js file defined in 'path' varriable
				 * userlist = new PageImpl<User>(list.subList(start, end), pageable,
				 * list.size());
				 */
				userlist = userRepository.findByText(dto.getText(), pageable);
				break;
				
			case "아이디":
				userlist = userRepository.findByUsernameContaining(dto.getText(), pageable); 
				break;
			case "이메일":
				userlist = userRepository.findByEmailContaining(dto.getText(), pageable);
				break;
		}
		return userlist;
	}
	
}
