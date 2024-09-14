package com.ca.account.manager.common.repos;

import com.ca.account.manager.common.repos.domain.IndexDatabase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexDatabaseRepositoryTest extends BaseIndexDatabaseRepositoryTest{

    @Test
    public void testFindAll(){

        IndexDatabase indexDatabase =new IndexDatabase();
        indexDatabase.setIdurl("url");
        indexDatabase.setIdusername("urs");
        indexDatabase.setIdpassword("pwd");
        indexDatabase.setIdschema("tenant1");
        indexDatabase.setIddialect("dialect");
        indexDatabase.setIdCode("code");
        indexDatabase.setIdState("active");
        indexDatabase.setIddriver("postgres");
        indexDatabase.setIdconfiguration("tmp");

        indexDatabaseRepository.save(indexDatabase);

        assertThat(indexDatabaseRepository.findAll()).doesNotContainNull();

    }
}
