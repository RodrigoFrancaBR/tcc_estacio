package br.com.franca.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.franca.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	 
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> objectNotFoundException(ObjectNotFoundException e,
			HttpServletRequest request) {
		StandartError err = new StandartError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	/*
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public
	 * ResponseEntity<StandartError> validation(MethodArgumentNotValidException e,
	 * HttpServletRequest request) { StandartError err = new
	 * StandartError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
	 * System.currentTimeMillis()); return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); }
	 */
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandartError> validation(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());		
		
		// acessa todos os erros de campos que vieram na exception MethodArgumentNotValidException
		for (FieldError e : ex.getBindingResult().getFieldErrors()){
			error.addError(e.getField(), e.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
