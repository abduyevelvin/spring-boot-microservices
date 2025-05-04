package com.java.employee.service;

import com.java.employee.dto.APIResponseDto;
import com.java.employee.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
