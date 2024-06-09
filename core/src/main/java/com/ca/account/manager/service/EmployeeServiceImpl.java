package com.ca.account.manager.service;

import com.ca.account.manager.dto.EmployeeDto;
import com.ca.account.manager.entity.Employee;
import com.ca.account.manager.exception.ResourceNotFound;
import com.ca.account.manager.mapper.EmployeeMapper;
import com.ca.account.manager.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements  EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }



    @Override
public EmployeeDto getEmployeeById(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId)

                .orElseThrow(() -> new ResourceNotFound("employye not existe"));
    return EmployeeMapper.mapToEmployeeDto(employee);
    }
}


