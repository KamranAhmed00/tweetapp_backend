package com.tweetapp.service.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.LikeDto;
import com.tweetapp.entity.Like;
import com.tweetapp.entity.Post;
import com.tweetapp.entity.User;
import com.tweetapp.exception.ResourceNotFoundException;
import com.tweetapp.repository.LikeRepo;
import com.tweetapp.repository.PostRepo;
import com.tweetapp.repository.UserRepo;
import com.tweetapp.service.LikeService;
import com.tweetapp.service.UserService;

@Service
public class LikeServiceImpl implements LikeService{
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private LikeRepo likeRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
  
	@Override
	public LikeDto like(Integer postId, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		Like like =new Like();
//				this.modelMapper.map(likeDto,Like.class);
//		if(!like.isStatus()) {
		like.setPost(post);
		like.setUser(user);
		like.setUId(userId);
		Like saveLike=this.likeRepo.save(like);
		return this.modelMapper.map(saveLike, LikeDto.class);
//		}
//		else {
//			this.likeRepo.unlike(userId,postId);
//			return this.modelMapper.map(like, LikeDto.class);
//		}
		
	}
 
	@Transactional
	@Override
	public boolean unlike(Integer postId, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		this.likeRepo.unlike(userId,postId);
		return true;
	}

	

	
}
