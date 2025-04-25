package com.java.employee.service.impl;

import com.java.employee.dto.EmployeeDto;
import com.java.employee.exception.EmailAlreadyExistsException;
import com.java.employee.exception.ResourceNotFoundException;
import com.java.employee.repository.EmployeeRepository;
import com.java.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.java.employee.mapper.EmployeeMapper.EMPLOYEE_MAPPER;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        var existingEmployee = employeeRepository.findByEmail(employeeDto.email());

        if (existingEmployee.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists for the employee");
        }

        // Convert DTO to Entity
        var employeeEntity = EMPLOYEE_MAPPER.toEmployeeEntity(employeeDto);

        var savedEmployee = employeeRepository.save(employeeEntity);

        return EMPLOYEE_MAPPER.toEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        var employeeEntity = employeeRepository.findById(employeeId)
                                               .orElseThrow(() -> new ResourceNotFoundException(
                                                               "Employee",
                                                               "id",
                                                               employeeId
                                                       )
                                               );

        return EMPLOYEE_MAPPER.toEmployeeDto(employeeEntity);
    }
}
