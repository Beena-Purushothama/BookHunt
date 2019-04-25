package com.beena.books.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beena.books.entity.Book;
import com.beena.books.entity.SearchKey;

public interface BooksRepository extends PagingAndSortingRepository<Book, String> {
    List<Book> findAllBySearchKeysOrderByTitle(SearchKey key,Pageable sortedByTitle);
    //List<Book> findAllOrderByTitle();
    Optional<Book> findById(String id);

}
