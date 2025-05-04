package com.java.employee.service.impl;

import com.java.employee.dto.APIResponseDto;
import com.java.employee.dto.EmployeeDto;
import com.java.employee.exception.EmailAlreadyExistsException;
import com.java.employee.exception.ResourceNotFoundException;
import com.java.employee.repository.EmployeeRepository;
import com.java.employee.service.APIClient;
import com.java.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.java.employee.mapper.EmployeeMapper.EMPLOYEE_MAPPER;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    //private final RestTemplate restTemplate;
    //private final WebClient webClient;
    private final APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        var existingEmployee = employeeRepository.findByEmail(employeeDto.email());

        if (existingEmployee.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists for the employee: " + employeeDto.email());
        }

        // Convert DTO to Entity
        var employeeEntity = EMPLOYEE_MAPPER.toEmployeeEntity(employeeDto);

        var savedEmployee = employeeRepository.save(employeeEntity);

        return EMPLOYEE_MAPPER.toEmployeeDto(savedEmployee);
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        var employeeEntity = employeeRepository.findById(employeeId)
                                               .orElseThrow(() -> new ResourceNotFoundException(
                                                               "Employee",
                                                               "id",
                                                               employeeId
                                                       )
                                               );

//        var responseEntity = restTemplate.getForEntity(
//                "http://localhost:8080/departments/" + employeeEntity.getDepartmentCode(),
//                DepartmentDto.class
//        );
//
//        var departmentDto = responseEntity.getBody();

//        var departmentDto = webClient.get()
//                                     .uri("http://localhost:8080/departments/" + employeeEntity.getDepartmentCode())
//                                     .retrieve()
//                                     .bodyToMono(DepartmentDto.class)
//                                     .block();

        var departmentDto = apiClient.getDepartmentByCode(employeeEntity.getDepartmentCode());

        var employeeDto = EMPLOYEE_MAPPER.toEmployeeDto(employeeEntity);

        return new APIResponseDto(employeeDto, departmentDto);
    }
}
