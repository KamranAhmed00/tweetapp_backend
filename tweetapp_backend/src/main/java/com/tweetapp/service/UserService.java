package com.tweetapp.service;

import java.util.List;

import com.tweetapp.dto.UserDto;

public interface UserService {

	UserDto registerUser(UserDto userDto);
//	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto,String email);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	List<UserDto> searchUser(String keyword);
	
}
