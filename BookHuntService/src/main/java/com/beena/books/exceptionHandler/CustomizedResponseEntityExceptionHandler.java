package com.beena.books.exceptionHandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BooksNotFoundException.class)
	  public final ResponseEntity<ErrorDetails> handleUserNotFoundException(BooksNotFoundException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    System.out.println("in hreerekhgh");
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	  }
	
	@ExceptionHandler(InvalidRequestParameterExeception.class)
	  public final ResponseEntity<ErrorDetails> handleInvalidRequestParameterException(InvalidRequestParameterExeception ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	  }
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
	  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	      request.getDescription(false));
	  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
