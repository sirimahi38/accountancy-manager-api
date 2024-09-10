package com.ca.account.manager.tasks.api;

import com.ca.account.manager.common.repos.domain.IndexDatabase;
import com.ca.account.manager.tasks.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ModelMapper modelMapper;

    public TaskController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("first")
    public String rtrvMe() {

        return "first";
    }

    @GetMapping
    public List<IndexDatabase> rtrvTaskList() {

        return taskService.rtrvAllTasks();
    }


}
