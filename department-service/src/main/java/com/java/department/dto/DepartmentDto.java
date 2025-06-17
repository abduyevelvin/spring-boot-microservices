package com.java.department.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(
        description = "DepartmentDTO Model Information"
)
public record DepartmentDto(
        Long departmentId,
        @Schema(
                description = "Department Name"
        )
        @NotNull(message = "Department Name cannot be null")
        String departmentName,
        @Schema(
                description = "Department Description"
        )
        @NotNull(message = "Department Description cannot be null")
        String departmentDescription,
        @Schema(
                description = "Department Code"
        )
        @NotNull(message = "Department Code cannot be null")
        String departmentCode
) {
}
