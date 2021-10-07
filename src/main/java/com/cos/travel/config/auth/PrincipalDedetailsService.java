package com.cos.travel.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.travel.model.User;
import com.cos.travel.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDedetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("나 실행됨?"+username);
		User userEntity = userRepository.findByUsername(username);
		if (userEntity==null) {
			return null;
		} else {
			return new PrincipalDetails(userEntity);
		}
	}

}
