package com.beena.books.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.beena.books.entity.Book;
import com.beena.books.entity.SearchKey;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BooksRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private BooksRepository booksRepository;
	
	@Test
	public void whenfindById_thenReturnBook(){
		//given
		Book book = Book.builder().id("abc").title("The Flower").imageLinks("http://testUrl.com").authors("Dan Brown").build();
		entityManager.persist(book);
		entityManager.flush();
		
		//when
		Optional<Book> found = booksRepository.findById(book.getId());
		
		//then
		if(found.isPresent()){
			assertThat(found.get().getTitle(),is(book.getTitle()));
			assertThat(found.get().getImageLinks(),is(book.getImageLinks()));
			assertThat(found.get().getAuthors(),is(book.getAuthors()));
		}
	}
	
	@Test
	public void whenFindAllBySearchKeysOrderByTitle_thenReturnBooks(){
		//given 
    	SearchKey key = SearchKey.builder().key("flower").build();
		Book book1 = Book.builder().id("abc").title("The Flower").imageLinks("http://testUrl.com").authors("Rabindranath Tagore").build();
		Book book2 = Book.builder().id("def").title("The Hat").imageLinks("http://testHatUrl.com").authors("Dan Brown").build();
		book1.addSearchKey(key);
		book2.addSearchKey(key);
		entityManager.persist(book1);
		entityManager.persist(book2);
		entityManager.flush();
		Pageable pageable =  PageRequest.of(0, 10);

		//when
	    List<Book> foundBooks= booksRepository.findAllBySearchKeysOrderByTitle(key,pageable);
	    
	    //then
	    assertThat(foundBooks.size(),is(2));

	}

}
