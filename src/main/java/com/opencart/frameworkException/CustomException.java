package com.opencart.frameworkException;

public class CustomException extends RuntimeException {
	
	public CustomException() {
		System.out.print("Encountered exception");
	}
	
	public CustomException(String message) {
		super(message);
	}
	public CustomException(String message, Throwable cause) {
		super(message,cause);
	}

}
