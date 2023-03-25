package com.tweetapp.security;

import com.tweetapp.dto.UserDto;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	private UserDto userDto;
}
