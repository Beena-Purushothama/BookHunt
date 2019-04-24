package com.beena.books.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.beena.books.entity.SearchKey;

public interface SearchKeyRepository extends PagingAndSortingRepository<SearchKey, Long>{
	
	SearchKey findByKey(String key);
	@Modifying
	@Transactional
    @Query(
            value = "TRUNCATE TABLE SEARCH_KEY",
            nativeQuery = true
    )
    void truncateMyTable();
	

}
