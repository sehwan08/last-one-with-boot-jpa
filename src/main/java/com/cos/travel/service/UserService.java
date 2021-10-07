package com.cos.travel.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.travel.model.User;
import com.cos.travel.repository.UserRepository;

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
		System.out.println("=======나 실행됨?======"+ id);
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
}
