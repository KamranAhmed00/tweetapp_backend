package com.tweetapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.ApiResponse;
import com.tweetapp.dto.PostDto;
import com.tweetapp.dto.UserDto;
import com.tweetapp.service.UserService;

@RestController
@RequestMapping("/api/v1.0/tweets")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/users/all")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return new ResponseEntity<>(this.userService.getAllUsers(),HttpStatus.OK);
	}
	
//	@GetMapping("/{userId}")
//	public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer userId){
//		return new ResponseEntity<>(this.userService.getUserById(userId),HttpStatus.OK);
//	}
	
//	@PostMapping("/createUser")
//	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
//		UserDto createUserDto=this.userService.createUser(userDto);
//		return new ResponseEntity<>(createUserDto,HttpStatus.OK);
//		
//	}
	
	@PutMapping("/{email}/forgot")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("email") String email){
		UserDto updateUser=this.userService.updateUser(userDto, email);
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}
	
	@GetMapping("/user/search/{keywords}")
	public ResponseEntity<List<UserDto>> serachUserByUserName(@PathVariable("keywords") String keywords){
		List<UserDto> result=this.userService.searchUser("%"+keywords+"%");
		return new ResponseEntity<List<UserDto>>(result,HttpStatus.OK);
		
	}
//	@DeleteMapping("/delete/{userId}")
//	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
//		this.userService.deleteUser(userId);
//		return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);	
//		//ResponseEntity(Map.of("message","User Deleted Successfully",HttpStatus.oks);
//	}

}
