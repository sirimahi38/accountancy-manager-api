package com.ca.account.manager.tasks.service;


import com.ca.account.manager.common.domain.EmployeeTask;
import com.ca.account.manager.common.repos.TaskRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<EmployeeTask> rtrvAllTasks(){

        return taskRepository.findAll();
    }
}
