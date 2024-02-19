package com.ca.account.manager.controller;

import com.ca.account.manager.model.Employee;
import com.ca.account.manager.dao.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeRepository employeeRepository;


    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(@RequestBody Employee employee){

        employeeRepository.save(employee);

        return "done";
    }

    @GetMapping("/hello/fetchEmployee")
    public List<Employee> fetchEmployees(Employee employee){
        return employeeRepository.findAll();
    }
}
