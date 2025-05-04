package com.java.employee.dto;

public record APIResponseDto(
        EmployeeDto employee,
        DepartmentDto department
) {
}
