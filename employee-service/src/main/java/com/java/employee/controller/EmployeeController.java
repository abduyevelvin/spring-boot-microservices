package com.java.employee.controller;

import com.java.employee.dto.APIResponseDto;
import com.java.employee.dto.EmployeeDto;
import com.java.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(
        name = "Employee Management",
        description = "APIs for managing employees"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(
            summary = "Save a new employee",
            description = "This endpoint allows you to create a new employee in the DB with the provided details."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Employee created successfully"
    )
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        var employee = employeeService.saveEmployee(employeeDto);

        return ResponseEntity.status(CREATED)
                             .body(employee);
    }

    @Operation(
            summary = "Get department by id",
            description = "This endpoint retrieves an employee by its id from the DB."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Employee retrieved successfully"
    )
    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        var employee = employeeService.getEmployeeById(employeeId);

        return ResponseEntity.ok(employee);
    }
}
