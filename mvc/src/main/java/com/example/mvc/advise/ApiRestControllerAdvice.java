package com.example.mvc.advise;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.mvc.model.ApiError;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ApiRestControllerAdvice {
		
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public ApiError handleException(NullPointerException e) {
		ApiError error = new ApiError(1, e.getMessage());
		return error;
	}
	
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ApiError handleException(Exception e) {
		ApiError error = new ApiError(0, e.getMessage());
		return error;
	}
	
}
