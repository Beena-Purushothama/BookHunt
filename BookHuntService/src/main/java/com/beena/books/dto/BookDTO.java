package com.beena.books.dto;

import java.util.HashMap;
import java.util.List;

import lombok.Data;

@Data
public class BookDTO {
	
	private String title;
	private String id;
	private List<String> authors; 
	private HashMap <String, String> imageLinks;

}
