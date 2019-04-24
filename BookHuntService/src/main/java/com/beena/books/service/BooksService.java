package com.beena.books.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.beena.books.dto.BookDTO;
import com.beena.books.entity.Book;
import com.beena.books.entity.SearchKey;
import com.beena.books.pojo.BooksVolumes;
import com.beena.books.pojo.Item;
import com.beena.books.repository.BooksRepository;
import com.beena.books.repository.SearchKeyRepository;

@Service
public class BooksService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	CacheManager cacheManager;
	
	@Autowired
	SearchKeyRepository searchKeyRepository;
	
	@Autowired
	BooksRepository booksRepository;
	
	@Cacheable("books")
	public List<BookDTO> fetchBooks(String query) {
		
	    ResponseEntity<BooksVolumes> forEntity = this.restTemplate.getForEntity( "https://www.googleapis.com/books/v1/volumes?maxResults=20&q=/"+query,
	            BooksVolumes.class);
	    List<Item> books = forEntity.getBody().getItems();
	    
	    SearchKey key = SearchKey.builder().key(query).build();
	    books.forEach((b) -> {
	    	Book book = Book.builder().id(b.getId()).title(b.getVolumeInfo().getTitle()).imageLinks(b.getVolumeInfo().getImageLinks().get("smallThumbnail")).build();
	    	key.addBooks(book);
	    });
	    
	    searchKeyRepository.save(key);
	   /* Collections.sort(books, (book1, book2) -> {
	    	return book1.getVolumeInfo().getTitle().compareToIgnoreCase(book2.getVolumeInfo().getTitle());
	    });*/
	   return null;
	}
	
	private List<BookDTO> convertToOrderDto(List<Book> books) {   
		List<BookDTO> bookDtos = new ArrayList<BookDTO>();
		books.forEach(book -> {
		BookDTO bookDto = modelMapper.map(book, BookDTO.class);
		bookDtos.add(bookDto);
		});
		System.out.println(bookDtos.size());
		return bookDtos;
	}
	
	@Scheduled(cron="0 10 * * * *")
	private void evictAllcachesAtIntervals() {
		System.out.println("clearing cache....");
	cacheManager.getCacheNames().stream()
    .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}

}
