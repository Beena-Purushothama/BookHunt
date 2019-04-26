package com.beena.books;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.beena.books.entity.Book;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BooksApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BooksApplicationIT {
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	//@Test
	public void fetchBooksWithValidInputs() {
		
		ResponseEntity<Map<String,Book>> response = restTemplate.exchange(
				createURLWithPort("/books/search?q=flower&page=1"),
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<Map<String,Book>>(){});
		
		Map<String,Book> bookMap = response.getBody();
		Boolean actual = response.getStatusCode().is2xxSuccessful();
		assertEquals(true, actual);
		assertNotNull(bookMap);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
