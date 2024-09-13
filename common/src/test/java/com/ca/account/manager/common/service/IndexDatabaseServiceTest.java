package com.ca.account.manager.common.service;

import com.ca.account.manager.common.CommonAdapterTest;
import com.ca.account.manager.common.repos.IndexDatabaseRepository;
import com.ca.account.manager.common.repos.domain.IndexDatabase;
import com.ca.account.manager.common.utils.DataSourceUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexDatabaseServiceTest extends CommonAdapterTest {

    @Autowired
    private IndexDatabaseService indexDatabaseService;

    @Test
    public void testGetIndexDataSourceConfiguration() {
        //setup
        IndexDatabaseRepository indexDatabaseRepository = Mockito.mock(IndexDatabaseRepository.class);
        Mockito.when(indexDatabaseRepository.findAll()).thenReturn(Collections.emptyList());
        IndexDatabaseService indexDatabaseService = new IndexDatabaseService(indexDatabaseRepository);
        //when
        List<IndexDatabase> indexDatabases = indexDatabaseService.rtrvAllIndexDatabases();
        //then
        assertThat(indexDatabases).asList();
    }

    @Test
    public void testCreateIndexDataSourceForGivenName() {
        //setup
        IndexDatabaseRepository indexDatabaseRepository = Mockito.mock(IndexDatabaseRepository.class);
        Mockito.when(indexDatabaseRepository.findAll()).thenReturn(Collections.emptyList());
        IndexDatabaseService indexDatabaseService = new IndexDatabaseService(indexDatabaseRepository);
        //when
        Mockito.when(indexDatabaseService.getDataSource(Mockito.anyString())).thenReturn(Mockito.mock(DataSource.class));
        DataSource dataSource = indexDatabaseService.getDataSource("test");
        //then
        assertThat(dataSource).isNotNull();
    }
}
