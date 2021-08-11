package com.vrs.entities;

import lombok.Data;

@Data
public class ErrorResponse {
	// for http status code
	private int status;
	// error message
	private String message;
	// time at which exception occured
	private long timeStamp;
	
}
