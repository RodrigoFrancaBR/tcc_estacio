package br.com.franca.resource.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {
	
	private List <FieldMessage> errors = new ArrayList<FieldMessage>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
	
	

}
