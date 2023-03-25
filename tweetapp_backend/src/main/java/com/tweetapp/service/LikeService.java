package com.tweetapp.service;

import com.tweetapp.dto.LikeDto;

public interface LikeService {

	LikeDto like(Integer postId,Integer userId);
	boolean unlike(Integer postId,Integer userId);
	
}
