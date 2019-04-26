package com.beena.books.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.beena.books.entity.Book;
import com.beena.books.entity.SearchKey;
import com.beena.books.repository.BooksRepository;
import com.beena.books.repository.SearchKeyRepository;

@RunWith(SpringRunner.class)
public class BookServiceTest {
	
	@TestConfiguration
    static class BookServiceImplTestContextConfiguration {
  
        @Bean
        public BooksService BookService() {
            return new BooksService();
        }
    }
	
	@Autowired
	BooksService bookService;
	
	@MockBean
	RestTemplate restTemplate;
	
	@MockBean
	CacheManager cacheManager;
	
	@MockBean
	BooksRepository  booksRepository;
	
	@MockBean
	SearchKeyRepository  searchKeyRepository; 
	
	@Before
	public void setUp() {
		SearchKey key = SearchKey.builder().key("flower").build();
		Book book1 = Book.builder().id("abc").title("The Flower").imageLinks("http://testUrl.com").authors("Rabindranath Tagore").build();
		Book book2 = Book.builder().id("def").title("The Hat").imageLinks("http://testHatUrl.com").authors("Dan Brown").build();
		book1.addSearchKey(key);
		book2.addSearchKey(key);
		Optional<Book> found = Optional.of(book1);
		Pageable pageable =  PageRequest.of(0, 10);
		List<Book> books = new ArrayList<>(); 
		books.add(book1);
		books.add(book2);
	 
	    Mockito.when(searchKeyRepository.findByKey(key.getKey()))
	      .thenReturn(key);
	    
	    Mockito.when(booksRepository.findById(book1.getId()))
	      .thenReturn(found);
	    
	    Mockito.when(booksRepository.findAllBySearchKeysOrderByTitle(key, pageable))
	      .thenReturn(books);
	}
	
	@Test
	public void whenFetchBooks_thenReturnBooks() {
	    Map<String,Book> books = bookService.fetchBooks("flower", 0);
	     assertThat(books.size(),is(2));      
	 }

}
