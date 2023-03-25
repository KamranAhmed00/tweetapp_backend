package com.tweetapp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.PostDto;
import com.tweetapp.dto.UserDto;
import com.tweetapp.entity.Post;
import com.tweetapp.entity.User;
import com.tweetapp.exception.DataIntegrity;
import com.tweetapp.exception.ResourceNotFoundException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.repository.RoleRepo;
import com.tweetapp.repository.UserRepo;
import com.tweetapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Override
//	public UserDto createUser(UserDto userDto) {
//		// TODO Auto-generated method stub
//		User user=this.dtoToUser(userDto);
//		User saveUser=this.userRepo.save(user);
//		return this.userToDto(saveUser);
//	}
	@Override
	public List<UserDto> searchUser(String keyword) {
		List<User> user=this.userRepo.searchContaining(keyword);
		List<UserDto> userDtos=user.stream().map((users)->this.modelMapper.map(users, UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}
	
	
	@Override
	public UserDto updateUser(UserDto userDto, String email) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findByEmail(email).orElseThrow(()->new UserNotFoundException("User","User Id",email));
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//		user.setAbout(userDto.getAbout());
		User updateUser = this.userRepo.save(user);
		UserDto userDto1=this.modelMapper.map(updateUser,UserDto.class);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
		return this.modelMapper.map(user,UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
		this.userRepo.delete(user);

	}
	
	public DataIntegrity checkValidate(UserDto userDto) {
		Map<String , String> resp=new HashMap<>();
		Optional<User> handle=userRepo.findByLoginHandle(userDto.getLoginHandle());
		Optional<User> email=userRepo.findByEmail(userDto.getEmail());
		Optional<User> contact=userRepo.findByContact(userDto.getContact());
		if(handle.isPresent()) {
			resp.put("loginHandle", "Login Handle is Already taken !!");
//			throw new DataIntegrity("Login Handle is Already taken !!");
		}
		if(email.isPresent()) {
			resp.put("email", "Email is Already Used !!");
//			throw new DataIntegrity("Email is Already Used !!");
		}
		if(contact.isPresent()) {
			resp.put("contact", "Contact is Already Used !!");
//			throw new DataIntegrity("Email is Already Used !!");
		}
		if(!resp.isEmpty()) {
			String utilMapString = resp
	                .entrySet()
	                .stream()
	                .map(e -> e.toString()).collect(Collectors.joining(","));
			throw  new DataIntegrity(utilMapString);
		}
		return null;
	}

	@Override
	public UserDto registerUser(UserDto userDto) {
//		try {
		checkValidate(userDto);
		User user =this.modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User userNew=userRepo.save(user);
		return this.modelMapper.map(userNew, UserDto.class);
//		}
//		catch(DataIntegrityViolationException ex) {
//			throw new DataIntegrity("Duplicate Data Found");
//		}
	}
	



	
}
