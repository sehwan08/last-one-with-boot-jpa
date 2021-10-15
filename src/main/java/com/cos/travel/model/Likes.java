package com.cos.travel.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "likes_uk", columnNames = { "blogId", "userId" }) })
public class Likes {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "blogId")

	@ManyToOne
	private Blog blog; // 1개의 블로그는는 여러번 좋아요를 받는다

	@JoinColumn(name = "userId")

	@ManyToOne
	private User user; // 1명의 유저는 여러번 좋아요를 한다

	private LocalDateTime createDate;

	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
