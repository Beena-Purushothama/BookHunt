package com.beena.books.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BooksNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BooksNotFoundException(String exception) {
	    super(exception);
	  }

}
