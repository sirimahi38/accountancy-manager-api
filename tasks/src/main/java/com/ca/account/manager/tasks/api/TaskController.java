package com.ca.account.manager.tasks.api;

import com.ca.account.manager.common.domain.EmployeeTask;
import com.ca.account.manager.tasks.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/task")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<EmployeeTask> rtrvTaskList() {

        return taskService.rtrvAllTasks();
    }
}
