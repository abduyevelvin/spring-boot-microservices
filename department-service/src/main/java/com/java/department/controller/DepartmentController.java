package com.java.department.controller;

import com.java.department.dto.DepartmentDto;
import com.java.department.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(
        name = "Department Management",
        description = "APIs for managing departments"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(
            summary = "Save a new department",
            description = "This endpoint allows you to create a new department in the DB with the provided details."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Department created successfully"
    )
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        var department = departmentService.saveDepartment(departmentDto);

        return ResponseEntity.status(CREATED)
                             .body(department);
    }

    @Operation(
            summary = "Get department by code",
            description = "This endpoint retrieves a department by its unique code from the DB."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Department retrieved successfully"
    )
    @GetMapping("/{code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("code") String departmentCode) {
        var department = departmentService.getDepartmentByCode(departmentCode);

        return ResponseEntity.ok(department);
    }
}
