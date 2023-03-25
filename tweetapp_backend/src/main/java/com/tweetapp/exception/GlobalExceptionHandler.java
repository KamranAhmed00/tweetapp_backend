package com.tweetapp.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tweetapp.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataIntegrity.class)
	public ResponseEntity<Map<String, String>> dataIntegrity(DataIntegrity ex){
		String message=ex.getMessage();
		Map<String, String> newMap = Stream.of(message.split("\\,"))
	            .collect(Collectors.toMap(t -> t.toString().split("=")[0], t -> t.toString().split("=")[1]));
//		Map<String, String> message=ex;
//		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<Map<String, String>>(newMap,HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse> userNotFoundException(UserNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> apiException(ApiException ex){
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String , String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
		String fieldName= ((FieldError)error).getField();
		String message=error.getDefaultMessage();
		resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(DataIntegrityViolationException.class)
//	public ResponseEntity<Map<String,String>> dataIntegrity(DataIntegrityViolationException ex){
//		Map<String , String> resp=new HashMap<>();
//		ex.getStackTrace().streams().forEach((error)->{
//		String fieldName= ((FieldError)error).getField();
//		String message=error.getDefaultMessage();
//		resp.put(fieldName, message);
//		});
//		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
//	}
	
}
