package com.ca.account.manager.tasks.service;


import com.ca.account.manager.common.domain.EmployeeTask;
import com.ca.account.manager.common.repos.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<EmployeeTask> rtrvAllTasks(){

        return taskRepository.findAll();
    }

    public Optional<EmployeeTask> rtrvTask(Long taskId){

        return taskRepository.findById(taskId);
    }

    public  void createTask(EmployeeTask employeeTask){
        taskRepository.save(employeeTask);
    }
}
