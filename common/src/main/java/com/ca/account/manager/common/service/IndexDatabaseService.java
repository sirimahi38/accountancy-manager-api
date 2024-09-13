package com.ca.account.manager.common.service;

import com.ca.account.manager.common.repos.domain.IndexDatabase;
import com.ca.account.manager.common.repos.IndexDatabaseRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class IndexDatabaseService implements ApplicationContextAware, InitializingBean {
    private IndexDatabaseRepository indexDatabaseRepository;

    private HashMap<String, DataSource> dataSources = new HashMap<>();
    private ApplicationContext applicationContext;

    public IndexDatabaseService(IndexDatabaseRepository indexDatabaseRepository) {
        this.indexDatabaseRepository = indexDatabaseRepository;
    }

    public List<IndexDatabase> rtrvAllIndexDatabases(){

        return indexDatabaseRepository.findAll();
    }

    public Map<String, DataSource> getAll() {
        List<IndexDatabase> configList = indexDatabaseRepository.findAll();
        for (IndexDatabase config : configList) {
            DataSource dataSource = getDataSource(config.getIdschema());
            dataSources.put(config.getIdschema(), dataSource);
        }
        return dataSources;
    }

    public DataSource getDataSource(String name) {
        if (dataSources.get(name) != null) {
            return dataSources.get(name);
        }
        DataSource dataSource = createDataSource(name);
        if (dataSource != null) {
            dataSources.put(name, dataSource);
        }
        return dataSource;
    }

    private DataSource createDataSource(String name) {
        Optional<IndexDatabase> config = indexDatabaseRepository.findById(name);
        if (config != null) {
            DataSourceBuilder factory = DataSourceBuilder
                    .create().driverClassName(config.get().getIddriver())
                    .username(config.get().getIdusername())
                    .password(config.get().getIdpassword())
                    .url(config.get().getIdurl());
            DataSource ds = factory.build();
            return ds;
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<IndexDatabase> configList = indexDatabaseRepository.findAll();
        for (IndexDatabase config : configList) {
            DataSource dataSource = getDataSource(config.getIdschema());
            dataSources.put(config.getIdschema(), dataSource);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext=applicationContext;
    }
}
