package com.tweetapp.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {

	private int id;
	@NotBlank(message = "Comment Should have Atleat 1 Character")
	@Size(min=1,max=144,message="Comment Should not Exceed 144 Length Limit")
	private String content;
	@Size(max=50,message="Comment Should not Exceed 50 Length Limit")
	private String title;
	private Date createdAt;
	private UserDto user;
}
