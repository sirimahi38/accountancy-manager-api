package com.ca.account.manager.common.repos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class IndexDatabaseRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private IndexDatabaseRepository indexDatabaseRepository;

    @Test
    public void testFindAll(){
         assertThat(indexDatabaseRepository).isNotNull();

    }
}
