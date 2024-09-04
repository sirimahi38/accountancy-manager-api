package com.ca.account.manager.common.service;

import com.ca.account.manager.common.domain.IndexDatabase;
import com.ca.account.manager.common.repos.IndexDatabaseRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndexDatabaseService {
    private IndexDatabaseRepository indexDatabaseRepository;

    public IndexDatabaseService(IndexDatabaseRepository indexDatabaseRepository) {
        this.indexDatabaseRepository = indexDatabaseRepository;
    }

    public List<IndexDatabase> rtrvAllIndexDatabases(){
        return indexDatabaseRepository.findAll();
    }

    @PostConstruct
    public void printAllDetails(){
       rtrvAllIndexDatabases().stream().forEach((System.out::println));
    }

}
