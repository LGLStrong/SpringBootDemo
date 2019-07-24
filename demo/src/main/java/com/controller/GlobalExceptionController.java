package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.exception.ProductNotFoundException;


@ControllerAdvice
public class GlobalExceptionController {
	static {
		System.out.println("GlobalExceptionController");
	}
	/*
	 * customized exception
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> exception(ProductNotFoundException exception) {
		System.out.println("ExceptionHandler");
	      return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	   }
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> allException(Exception e){
		System.out.println("GlobalController got the exception"+e.getMessage());
		return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
	}
}
