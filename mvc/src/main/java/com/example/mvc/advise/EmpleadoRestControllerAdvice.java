package com.example.mvc.advise;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.mvc.exception.ElementNotFoundException;
import com.example.mvc.model.ApiError;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EmpleadoRestControllerAdvice {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		
		String errorText = "";
		
		for (FieldError fieldError : e.getFieldErrors()) {
			errorText += fieldError.getField() + ":" + fieldError.getDefaultMessage() + " -- ";
		}
		
		ApiError error = new ApiError(400, errorText);
		return error;
	}
	
	@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ApiError handleNotAcceptable(HttpMediaTypeNotSupportedException e) {
		ApiError error = new ApiError(6, e.getMessage());
		return error;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ApiError handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
		ApiError error = new ApiError(403, e.getMessage());
		return error;
	}
	
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ElementNotFoundException.class)
	public ApiError handleElementNotFoundException(ElementNotFoundException e) {
		ApiError error = new ApiError(404, e.getMessage());
		
		return error;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public ApiError handleException(NullPointerException e) {
		ApiError error = new ApiError(1, "Valor nulo");
		return error;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ApiError handleException(Exception e) {
		ApiError error = new ApiError(0, e.getMessage());
		return error;
	}
	
}
