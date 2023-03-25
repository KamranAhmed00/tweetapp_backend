package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.LikeDto;
import com.tweetapp.service.LikeService;

@RestController
@RequestMapping("/api/v1.0/tweets")
public class LikeController {

	@Autowired
	private LikeService likeService;
	
	@PostMapping("/{userId}/like/{postId}")
	public ResponseEntity<LikeDto> likePost(@PathVariable Integer userId,@PathVariable Integer postId) {
		LikeDto likeDtos = this.likeService.like(postId, userId);
		return new ResponseEntity<LikeDto>(likeDtos, HttpStatus.CREATED);
	}
	@DeleteMapping("/{userId}/unlike/{postId}")
	public ResponseEntity<Boolean> unlikePost(@PathVariable Integer userId,@PathVariable Integer postId) {
		return new ResponseEntity<Boolean>( this.likeService.unlike(postId, userId), HttpStatus.CREATED);
	}
}
