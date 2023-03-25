package com.tweetapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.ApiResponse;
import com.tweetapp.dto.CommentDto;
import com.tweetapp.service.CommentService;

@RestController
@RequestMapping("/api/v1.0/tweets")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/{postId}/reply/{userId}")
	public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto comment,@PathVariable("postId") Integer postId,@PathVariable("userId") Integer userId){
		CommentDto comments=this.commentService.createComment(comment, postId,userId);
		return new ResponseEntity<CommentDto>(comments,HttpStatus.OK);
	}
	
	@DeleteMapping("/comments/{commentsId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentsId") Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully !!",true),HttpStatus.OK);
	}
}
