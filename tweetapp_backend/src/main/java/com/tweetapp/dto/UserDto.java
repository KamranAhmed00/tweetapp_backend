package com.tweetapp.dto;

import java.sql.Date;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	@NotEmpty(message = "Should Not be Empty")
	@Size(min = 3,message = "FirstName Length Should be Valid")
	@Pattern(regexp = "^[a-zA-Z ]*$",message = "Only Alphabets Allowed")
	private String firstName;
	@NotEmpty(message = "Should Not be Empty")
	@Size(min = 3,message = "LastName Length Should be Valid")
	@Pattern(regexp = "^[a-zA-Z ]*$",message = "Only Alphabets Allowed")
	private String lastName;
	@NotBlank(message = "Email Address is Required !!")
	@Email
	private String email;
	@NotEmpty(message="LoginHandle is Required !!")
	@Pattern(regexp = "^[@][a-zA-Z0-9!#\\$%\\^\\&*\\)\\(+=._-]*$",message = "User Handle must start with @")
	private String loginHandle;
	@NotBlank(message = "Gender is Required !!")
	private String gender;
	@NotNull(message="Date Of Birth is Required!!")
	private Date dob;
	@NotBlank(message="Contact Number is Required!!")
	@Digits(integer = 10, fraction = 0,message = "Contact Number Should be of 10 Digits")
	private String contact;
	
	@NotEmpty(message="Password is Required !!")
	@Size(min=6,max = 15,message = "Password must be between 6 to 15 Chars !!")
	private String password;
	
//	@NotNull
//	private String confirmPassword;
}
