package com.tweetapp.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.JwtAuthRequest;
import com.tweetapp.dto.UserDto;
import com.tweetapp.exception.ApiException;
import com.tweetapp.security.CustomUserDetailUserService;
import com.tweetapp.security.JwtAuthResponse;
import com.tweetapp.security.JwtTokenHelper;
import com.tweetapp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/tweets")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private CustomUserDetailUserService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request){
		this.authenticate(request.getUsername(),request.getPassword());
		UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
		String token=this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		response.setUserDto(this.modelMapper.map(userDetails, UserDto.class));
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
		UserDto register=this.userService.registerUser(userDto);
		return new ResponseEntity<UserDto>(register,HttpStatus.CREATED);
	}
	
	private void authenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(username, password);
		try{
			this.authenticationManager.authenticate(authToken);
		}catch(BadCredentialsException e) {
			throw new ApiException("Invalid Username or Password");
		}
	}
}
