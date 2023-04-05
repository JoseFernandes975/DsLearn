package com.devsuperior.dslearnbds.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dslearnbds.exceptions.DataBaseException;
import com.devsuperior.dslearnbds.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(HttpServletRequest request, ResourceNotFoundException e){
		StandardError error = new StandardError();
		HttpStatus status = HttpStatus.NOT_FOUND;
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Entity Not Found!");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBaseException(HttpServletRequest request, DataBaseException e){
		StandardError error = new StandardError();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Database Error!");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(HttpServletRequest request, MethodArgumentNotValidException e){
		ValidationError error = new ValidationError();
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Validation Error!");
		error.setMessage(e.getMessage());
		
		for(FieldError f: e.getBindingResult().getFieldErrors()) {
			error.addErrors(f.getField(), f.getDefaultMessage());
		}
		
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}

}
