package com.tweetapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tweetapp.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
	Optional<User> findByLoginHandle(String loginHandle);
	Optional<User> findByContact(String contact);
	
	@Query("select p from User p where concat(p.firstName,' ',p.lastName) like:key")
	List<User> searchContaining(@Param("key")String keyword);
}
