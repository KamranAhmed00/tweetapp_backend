package com.tweetapp.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class JwtAuthRequest {

	@NotBlank(message="Should not be Empty")
	private String username;
	@NotBlank(message="Should not be Empty")
	private String password;
}
