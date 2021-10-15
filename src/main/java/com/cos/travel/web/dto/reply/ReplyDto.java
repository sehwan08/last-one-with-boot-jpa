package com.cos.travel.web.dto.reply;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {
	private int userId;
	private int blogId;
	private int replyId;
	private String content;
}
