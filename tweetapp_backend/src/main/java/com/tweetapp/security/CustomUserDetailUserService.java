package com.tweetapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tweetapp.entity.User;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.repository.UserRepo;

@Service
public class CustomUserDetailUserService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepo.findByEmail(username).orElseThrow(()->new UserNotFoundException("User", "email",username));
		return user;
	}

}
