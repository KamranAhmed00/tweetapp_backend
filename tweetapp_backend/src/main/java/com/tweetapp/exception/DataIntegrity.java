package com.tweetapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DataIntegrity extends RuntimeException{
	
	
	public DataIntegrity(String message) {
		super(message);
	}
	private DataIntegrity() {
		super();
	}

}
