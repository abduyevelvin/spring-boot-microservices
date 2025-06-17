package com.example.organization.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@Schema(
        description = "OrganizationDTO Model Information"
)
public record OrganizationDto(
        Long organizationId,
        @Schema(
                description = "Organization Name"
        )
        @NotNull(message = "Organization Name cannot be null")
        String organizationName,
        @Schema(
                description = "Organization Description"
        )
        String organizationDescription,
        @Schema(
                description = "Organization Code"
        )
        @NotNull(message = "Organization Code cannot be null")
        String organizationCode,
        @Schema(
                description = "Creation Date"
        )
        OffsetDateTime creationDate
) {
}
