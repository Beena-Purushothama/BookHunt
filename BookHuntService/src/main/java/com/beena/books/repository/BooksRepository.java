package com.beena.books.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.beena.books.entity.Book;

public interface BooksRepository extends PagingAndSortingRepository<Book, String> {

}
