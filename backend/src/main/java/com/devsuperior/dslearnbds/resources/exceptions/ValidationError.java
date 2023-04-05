package com.devsuperior.dslearnbds.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError() {
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	public void addErrors(String fieldMessage, String message){
		errors.add(new FieldMessage(fieldMessage, message));
	}

}
