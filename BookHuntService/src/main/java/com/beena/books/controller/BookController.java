package com.beena.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beena.books.entity.Book;
import com.beena.books.exceptionHandler.BooksNotFoundException;
import com.beena.books.exceptionHandler.InvalidRequestParameterExeception;
import com.beena.books.service.BooksService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	BooksService booksService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Book>> fetchBooks(@RequestParam("q") String query, @RequestParam("page") int page ){
		
		if(page >3)
			throw new InvalidRequestParameterExeception("Page request param value must be between 0-3");
		if(query.matches("[^a-z A-Z0-9]"))
			throw new InvalidRequestParameterExeception("Sprical charecters not allowed in query-"+query);
		List<Book> filteredBooks = booksService.fetchBooks(query,page);
		if(filteredBooks == null || filteredBooks.isEmpty())
			throw new BooksNotFoundException("Books not found for query"+query+" at page-"+page);
		return new ResponseEntity<List<Book>>(filteredBooks, HttpStatus.OK) ;
		
	}

	/*@ExceptionHandler(PaginationSortingException.class)
	public ResponseEntity<PagingSortingErrorResponse> exceptionHandler(Exception ex) {
	PagingSortingErrorResponse pagingSortingErrorResponse = new PagingSortingErrorResponse();
	pagingSortingErrorResponse.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
	pagingSortingErrorResponse.setMessage(ex.getMessage());
	return new ResponseEntity<PagingSortingErrorResponse>(pagingSortingErrorResponse, HttpStatus.OK);
	}*/
}
