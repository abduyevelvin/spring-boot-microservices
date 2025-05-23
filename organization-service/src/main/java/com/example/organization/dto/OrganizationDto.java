package com.example.organization.dto;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record OrganizationDto(
        Long organizationId,
        @NotNull(message = "Organization Name cannot be null")
        String organizationName,
        String organizationDescription,
        @NotNull(message = "Organization Code cannot be null")
        String organizationCode,
        OffsetDateTime creationDate
) {
}
