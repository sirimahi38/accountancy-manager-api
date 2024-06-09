package com.ca.account.manager.service;

import com.ca.account.manager.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);



    EmployeeDto getEmployeeById(Long employeeId);
}
