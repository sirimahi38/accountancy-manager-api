package com.ca.account.manager.tasks.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;

    @Autowired
    private WebApplicationContext wac;

    @Test
    public void rtrvTask()throws Exception{

        when(taskController.rtrvMe()).thenReturn("Hello");
        MockMvc mockMvc = webAppContextSetup(wac).build();
        mockMvc.perform(get("/accountant")).andExpectAll(status().isOk(),content().contentType("text/html"),forwardedUrl("/accountant/tasks/me"));
    }

}
