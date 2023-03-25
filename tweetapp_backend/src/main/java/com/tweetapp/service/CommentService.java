package com.tweetapp.service;

import com.tweetapp.dto.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);
	void deleteComment(Integer commentId);
	
}
