package com.beena.books.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestParameterExeception extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public InvalidRequestParameterExeception(String exception) {
	    super(exception);
	  }

}
