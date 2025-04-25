package com.java.department.controller;

import com.java.department.dto.DepartmentDto;
import com.java.department.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    // Build the REST API for saving department
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        var department = departmentService.saveDepartment(departmentDto);

        return ResponseEntity.status(CREATED)
                             .body(department);
    }

    // Build the REST API for getting department by code
    @GetMapping("/{code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("code") String departmentCode) {
        var department = departmentService.getDepartmentByCode(departmentCode);

        return ResponseEntity.ok(department);
    }
}
