package com.java.employee.controller;

import com.java.employee.dto.EmployeeDto;
import com.java.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Build the REST API for saving employee
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        var employee = employeeService.saveEmployee(employeeDto);

        return ResponseEntity.status(CREATED)
                             .body(employee);
    }

    // Build the REST API for getting employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        var employee = employeeService.getEmployeeById(employeeId);

        return ResponseEntity.ok(employee);
    }
}
