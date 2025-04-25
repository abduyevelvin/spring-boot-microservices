package com.java.department.dto;

import jakarta.validation.constraints.NotNull;

public record DepartmentDto(
        Long departmentId,
        @NotNull(message = "Department Name cannot be null")
        String departmentName,
        @NotNull(message = "Department Description cannot be null")
        String departmentDescription,
        @NotNull(message = "Department Code cannot be null")
        String departmentCode
) {
}
