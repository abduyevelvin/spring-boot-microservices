package com.java.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Schema(
        description = "EmployeeDTO Model Information"
)
public record EmployeeDto(
        Long employeeId,
        @Schema(
                description = "Employee First Name"
        )
        @NotNull(message = "Employee First Name cannot be null")
        String firstName,
        @Schema(
                description = "Employee Last Name"
        )
        @NotNull(message = "Employee Last Name cannot be null")
        String lastName,
        @Schema(
                description = "Employee Email"
        )
        @NotNull(message = "Employee Email cannot be null")
        @Email(message = "Employee Email should be valid")
        String email,
        @Schema(
                description = "Employee Department Code"
        )
        @NotNull(message = "Department Code cannot be null")
        String departmentCode,
        @Schema(
                description = "Employee Organization Code"
        )
        @NotNull(message = "Organization Code cannot be null")
        String organizationCode
) {
}
