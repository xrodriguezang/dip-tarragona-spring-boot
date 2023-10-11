package com.example.mvc.exception;

public class ElementNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -835029703049914655L;
	
	public ElementNotFoundException(String message) {
		super(message);
	}

}
