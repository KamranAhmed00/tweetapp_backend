package com.tweetapp.service.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.CommentDto;
import com.tweetapp.dto.UserDto;
import com.tweetapp.entity.Comment;
import com.tweetapp.entity.Post;
import com.tweetapp.entity.User;
import com.tweetapp.exception.ResourceNotFoundException;
import com.tweetapp.repository.CommentRepo;
import com.tweetapp.repository.PostRepo;
import com.tweetapp.repository.UserRepo;
import com.tweetapp.service.CommentService;
import com.tweetapp.service.UserService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		Comment comment =this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		comment.setCreatedAt(new Date());
		Comment savedComment=this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}
//	UserDto userGet=userService.getUserById(user.getId());
//	this.modelMapper.map(user, UserDto.class);
//	UserDto userDto=new UserDto();
//	userDto.setEmail(user.getEmail());
//	System.out.println(userDto);
//	System.out.println(user);
//	System.out.println(post);
//	this.modelMapper.map(userGet, User.class);
			//this.modelMapper.map(this.modelMapper.map(user, UserDto.class), User.class);
//	Post postGet=this.modelMapper.map(this.modelMapper.map(post, PostDto.class), Post.class);

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "comment id", commentId));
		this.commentRepo.delete(comment);
	}

}
