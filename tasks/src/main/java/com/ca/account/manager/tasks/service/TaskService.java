package com.ca.account.manager.tasks.service;


import com.ca.account.manager.common.repos.domain.IndexDatabase;
import com.ca.account.manager.common.repos.IndexDatabaseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskService {

    private final IndexDatabaseRepository indexDatabaseRepository;
    public TaskService(IndexDatabaseRepository indexDatabaseRepository) {
        this.indexDatabaseRepository = indexDatabaseRepository;
    }

    public List<IndexDatabase> rtrvAllTasks(){

        return indexDatabaseRepository.findAll();
 }

}
