package com.tweetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetapp.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
