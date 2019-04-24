package com.beena.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beena.books.dto.BookDTO;
import com.beena.books.service.BooksService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	BooksService booksService;
	
	@GetMapping("/search")
	public ResponseEntity<List<BookDTO>> fetchBooks(@RequestParam("q") String query){
		if(query.isEmpty()) {
			return null;
		}
		return new ResponseEntity<List<BookDTO>>(booksService.fetchBooks(query), HttpStatus.OK) ;
		
	}

}
