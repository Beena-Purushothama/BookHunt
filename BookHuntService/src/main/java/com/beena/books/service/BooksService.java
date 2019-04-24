package com.beena.books.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	private static final int MAX_PAGE_SIZE = 10;

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
	
	@Value("${google.booksapi.url}")
	String url;
	
	//@Cacheable("books")
	public List<Book> fetchBooks(String query, int page) {
		query = query.toLowerCase().trim();
		SearchKey keyFound = searchKeyRepository.findByKey(query);
		if(keyFound == null){
			keyFound = fetchBooksFromApi(query);
		}
		Pageable sortedByTitle =  PageRequest.of(page, MAX_PAGE_SIZE, Sort.by("title"));
		List<Book> books = booksRepository.findAllBySearchKeys(keyFound, sortedByTitle);
		
		books.forEach(b -> { System.out.println("book:" + b.getTitle());});
		
		return books;
	}
	
	private SearchKey fetchBooksFromApi(String query) {
		ResponseEntity<BooksVolumes> forEntity = this.restTemplate.getForEntity( url+query,BooksVolumes.class);
	    List<Item> items = forEntity.getBody().getItems();	
	    return persistKeyAndBooks(items, query);
	}

	private SearchKey persistKeyAndBooks(List<Item> items, String query) {
		SearchKey key = SearchKey.builder().key(query.toLowerCase().trim()).build();
		/*Collections.sort(items, (item1, item2) -> {
	    	return item1.getVolumeInfo().getTitle().compareToIgnoreCase(item2.getVolumeInfo().getTitle());
		});*/
	    items.forEach(b -> {
	    	Book book = Book.builder().id(b.getId()).title(b.getVolumeInfo().getTitle()).imageLinks(b.getVolumeInfo().getImageLinks().get("smallThumbnail")).build();
	    	key.addBooks(book);
	    });  
	    return searchKeyRepository.save(key);		
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
