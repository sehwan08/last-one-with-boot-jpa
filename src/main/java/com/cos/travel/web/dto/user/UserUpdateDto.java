package com.cos.travel.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.cos.travel.model.User;

import lombok.Data;

@Data
public class UserUpdateDto {
	
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
				.password(password)
				.email(email)
				.age(age)
				.gender(gender)
				.preference(preference)
				.build();
	}
}
