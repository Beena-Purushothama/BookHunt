package com.beena.books.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.beena.books.entity.Book;
import com.beena.books.entity.SearchKey;

public interface BooksRepository extends PagingAndSortingRepository<Book, String> {
    List<Book> findAllBySearchKeysOrderByTitle(SearchKey key,Pageable sortedByTitle);
    Optional<Book> findById(String id);
    @Modifying
	@Transactional
    @Query(
            value = "SET REFERENTIAL_INTEGRITY FALSE;"
            		+ "TRUNCATE TABLE BOOK_SEARCH ;"
            		+ "TRUNCATE TABLE BOOK;"
            		+ "TRUNCATE TABLE SEARCH_KEY;"
            		+ "SET REFERENTIAL_INTEGRITY TRUE;",
            nativeQuery = true
    )
    void truncateMyTable();

}
