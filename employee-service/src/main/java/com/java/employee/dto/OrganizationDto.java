package com.java.employee.dto;

import java.time.OffsetDateTime;

public record OrganizationDto(
        Long organizationId,
        String organizationName,
        String organizationDescription,
        String organizationCode,
        OffsetDateTime creationDate
) {
}
