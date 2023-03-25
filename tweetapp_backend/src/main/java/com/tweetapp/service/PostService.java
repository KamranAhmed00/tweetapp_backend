package com.tweetapp.service;

import java.util.List;

import com.tweetapp.dto.PostDto;
import com.tweetapp.dto.PostResponse;
import com.tweetapp.entity.Post;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId);//,Integer categoryId
	PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	PostDto getPostById(Integer postId);
//	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostByUser(Integer userId);
	
}
