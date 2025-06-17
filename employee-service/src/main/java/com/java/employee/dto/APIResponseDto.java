package com.java.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "API response containing employee, department, and organization details."
)
public record APIResponseDto(
        @Schema(description = "Details of the employee.")
        EmployeeDto employee,
        @Schema(description = "Details of the department to which the employee belongs.")
        DepartmentDto department,
        @Schema(description = "Details of the organization to which the employee belongs.")
        OrganizationDto organization
) {
}
