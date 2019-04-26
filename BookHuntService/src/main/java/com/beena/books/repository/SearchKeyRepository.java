package com.beena.books.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.beena.books.entity.SearchKey;

public interface SearchKeyRepository extends PagingAndSortingRepository<SearchKey, Long>{
	
	SearchKey findByKey(String key);
}
