package com.java.employee.service.impl;

import com.java.employee.dto.APIResponseDto;
import com.java.employee.dto.DepartmentDto;
import com.java.employee.dto.EmployeeDto;
import com.java.employee.dto.OrganizationDto;
import com.java.employee.exception.EmailAlreadyExistsException;
import com.java.employee.exception.ResourceNotFoundException;
import com.java.employee.repository.EmployeeRepository;
import com.java.employee.service.DepartmentAPIClient;
import com.java.employee.service.EmployeeService;
import com.java.employee.service.OrganizationAPIClient;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.java.employee.mapper.EmployeeMapper.EMPLOYEE_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    //private final RestTemplate restTemplate;
    //private final WebClient webClient;
    private final DepartmentAPIClient departmentAPIClient;
    private final OrganizationAPIClient organizationAPIClient;

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

    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        log.info("Fetching employee with ID: {}", employeeId);
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

        var departmentDto = departmentAPIClient.getDepartmentByCode(employeeEntity.getDepartmentCode());

        var organizationDto = organizationAPIClient.getOrganizationByCode(employeeEntity.getOrganizationCode());

        var employeeDto = EMPLOYEE_MAPPER.toEmployeeDto(employeeEntity);

        return new APIResponseDto(employeeDto, departmentDto, organizationDto);
    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Throwable throwable) {

        log.info("Fetching default department for employee with ID: {}", employeeId);
        var employeeEntity = employeeRepository.findById(employeeId)
                                               .orElseThrow(() -> new ResourceNotFoundException(
                                                               "Employee",
                                                               "id",
                                                               employeeId
                                                       )
                                               );

        var departmentDto = new DepartmentDto(
                0L,
                "R&D Department",
                "Research and Development Department",
                "RD001"
        );

        var organizationDto = new OrganizationDto(
                0L,
                "Default Organization",
                "Default Organization Description",
                "ORG001",
                null
        );

        var employeeDto = EMPLOYEE_MAPPER.toEmployeeDto(employeeEntity);

        return new APIResponseDto(employeeDto, departmentDto, organizationDto);
    }
}
