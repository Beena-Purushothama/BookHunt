package com.beena.books.repository;


import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.beena.books.entity.SearchKey;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SearchKeyRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private SearchKeyRepository serviceKeyRepository;
    
    @Test
    public void whenFindByKey_thenReturnSearchKey() {
        // given
    	SearchKey key = SearchKey.builder().key("flower").build();
        entityManager.persist(key);
        entityManager.flush();
     
        // when
        SearchKey found = serviceKeyRepository.findByKey(key.getKey());
     
        // then
        assertThat(found.getKey(), is(key.getKey()));

    }
}
