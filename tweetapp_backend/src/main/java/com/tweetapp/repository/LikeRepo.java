package com.tweetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tweetapp.entity.Like;

public interface LikeRepo extends JpaRepository<Like, Integer>{

	@Modifying
	@Query("delete from Like  where user_id=:keyUser AND post_post_id=:keyPost")
	void unlike(@Param("keyUser")Integer userId,@Param("keyPost")Integer postId);
}
