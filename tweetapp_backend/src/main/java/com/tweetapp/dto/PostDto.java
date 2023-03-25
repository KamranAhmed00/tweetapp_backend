package com.tweetapp.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Size;

import com.tweetapp.entity.Like;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	private Integer postId;
	@Size(max=50,message="Tweet Tag Should not Exceed 50 Length Limit")
	private String title;
	
	private String content;

	private Date addedDate;

	private UserDto user;
	private Set<CommentDto> comments=new HashSet<>();
	private Set<LikeDto> likes=new HashSet<>();
}
