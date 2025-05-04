package com.java.employee.dto;

public record DepartmentDto(
        Long departmentId,
        String departmentName,
        String departmentDescription,
        String departmentCode
) {
}
