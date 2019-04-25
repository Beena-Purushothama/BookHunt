package com.beena.books.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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
	public Map<String,Book> fetchBooks(String query, int page) {
		System.out.println("Executing method");
		Map<String,Book> bookMap = new LinkedHashMap<String,Book>(); 
		query = query.toLowerCase().trim();
		SearchKey keyFound = searchKeyRepository.findByKey(query);
		Pageable sortedByTitle =  PageRequest.of(page, MAX_PAGE_SIZE);

		if(keyFound == null){
			fetchBooksFromApi(query);
			keyFound = searchKeyRepository.findByKey(query);
		}
		
		List<Book> books = booksRepository.findAllBySearchKeysOrderByTitle(keyFound,sortedByTitle);
		
		books.forEach(b -> { 
			
			bookMap.put(b.getId(), b);});
		return bookMap;
	}
	
	private void fetchBooksFromApi(String query) {
		ResponseEntity<BooksVolumes> forEntity = this.restTemplate.getForEntity( url+query,BooksVolumes.class);
	    List<Item> items = forEntity.getBody().getItems();	
	    persistKeyAndBooks(items, query);
	    return ;
	}

	private void persistKeyAndBooks(List<Item> items, String query) {
		SearchKey key = SearchKey.builder().key(query.toLowerCase().trim()).build();
		SearchKey savedKey = searchKeyRepository.save(key);
		Set<Book> books = new HashSet<Book>();
	    items.forEach(b -> {
	    	Optional<Book> bookOptional = booksRepository.findById(b.getId());
	    	Book book = null;
	    	if(bookOptional.isPresent()) {
	    		System.out.println("found book already"+bookOptional.get().getId());
	    		book=bookOptional.get();
	    	}else{
	    		HashMap<String, String> imageLinks = b.getVolumeInfo().getImageLinks();
		    	String imageLink = (imageLinks == null)? "" :imageLinks.get("smallThumbnail");
		    	List<String> authors =  b.getVolumeInfo().getAuthors();
		    	String author = (authors == null )? "" :String.join(",", authors);
		    	System.out.println("id="+b.getId()+" ; title =" +b.getVolumeInfo().getTitle());
		    	book = Book.builder().id(b.getId())
		    			.title(b.getVolumeInfo().getTitle())
		    			.imageLinks(imageLink)
		    			.authors(author)
		    			.build();
		    	
	    	}
	    	book.getSearchKeys().add(savedKey);
	    	books.add(book);
	    });  
	    
	    booksRepository.saveAll(books);
	    
	   // return searchKeyRepository.save(key);		
	}
	
	@Scheduled(cron="0 0/5 * * * *")
	public void evictAllcachesAtIntervals() {
		System.out.println("clearing cache....");
	cacheManager.getCacheNames().stream()
    .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}
	
	//@Scheduled(cron="0 15 * * * *")
	@Transactional
	private void truncateTables() {
		System.out.println("Table Truncate....");
		searchKeyRepository.truncateMyTable();
	}

}
