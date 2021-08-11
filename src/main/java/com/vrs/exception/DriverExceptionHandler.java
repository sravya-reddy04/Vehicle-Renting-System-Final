package com.vrs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vrs.entities.ErrorResponse;

@ControllerAdvice
public class DriverExceptionHandler {
	//handling all driver not found exception here
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(DriverNotFoundException ex){
		ErrorResponse error = new ErrorResponse();

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	//handling all driver found exception here
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(DriverFoundException ex){
		ErrorResponse error = new ErrorResponse();

		error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
