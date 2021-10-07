package com.cos.travel.web.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cos.travel.model.User;

import lombok.Data;

@Data
public class JoinDto {
	
	@Size(min = 2, max = 20)
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String age;
	
	@NotBlank
	private String gender;
	
	@NotBlank
	private String preference;
	
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.age(age)
				.gender(gender)
				.preference(preference)
				.build();
	}
}
