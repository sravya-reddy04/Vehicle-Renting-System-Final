package com.vrs.exception;

public class DriverNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public DriverNotFoundException(String message) {
		super(message);
	}
}
