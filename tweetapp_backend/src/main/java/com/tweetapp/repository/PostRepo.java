package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tweetapp.entity.Post;
import com.tweetapp.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
//	List<Post> findByCategory(Category category);
//	@Query("select p from Post p where p.title like:key")
//	List<Post> searchContaining(@Param("key")String title);
	//List<Post> findByTitleContaining(@Param("key")String title);
}
