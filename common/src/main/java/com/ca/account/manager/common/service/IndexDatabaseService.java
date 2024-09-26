package com.ca.account.manager.common.service;

import com.ca.account.manager.common.repos.IndexDatabaseRepository;
import com.ca.account.manager.common.repos.domain.IndexDatabase;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class IndexDatabaseService implements ApplicationContextAware, InitializingBean {
    private final IndexDatabaseRepository indexDatabaseRepository;

    private Map<String, DataSource> dataSources = new ConcurrentHashMap<>();

    public IndexDatabaseService(IndexDatabaseRepository indexDatabaseRepository) {
        this.indexDatabaseRepository = indexDatabaseRepository;
    }

    public DataSource getDataSource(String name) {
        if (dataSources.get(name) != null) return dataSources.get(name);
        DataSource dataSource = createDataSource(name);
        if (dataSource != null) dataSources.put(name, dataSource);
        return dataSource;
    }

    private DataSource createDataSource(String name) {
        Optional<IndexDatabase> indexDatabase = indexDatabaseRepository.findById(name);
        return indexDatabase.map(database -> DataSourceBuilder
                .create().driverClassName(database.getIddriver())
                .username(database.getIdusername())
                .password(database.getIdpassword())
                .url(database.getIdurl()).build()).orElse(null);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<IndexDatabase> indexDatabaseList = indexDatabaseRepository.findAll();
        dataSources = indexDatabaseList.stream().collect(Collectors.toConcurrentMap(IndexDatabase::getIdschema, indexDatabase -> (getDataSource(indexDatabase.getIdschema()))));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }
}
