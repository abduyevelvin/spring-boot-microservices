package com.example.organization.controller;

import com.example.organization.dto.OrganizationDto;
import com.example.organization.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(
        name = "Organization Management",
        description = "APIs for managing organizations"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Operation(
            summary = "Save a new organization",
            description = "This endpoint allows you to create a new organization in the DB with the provided details."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Organization created successfully"
    )
    @PostMapping
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody @Valid OrganizationDto organizationDto) {
        var organization = organizationService.saveOrganization(organizationDto);

        return ResponseEntity.status(CREATED)
                             .body(organization);
    }

    @Operation(
            summary = "Get organization by code",
            description = "This endpoint retrieves a organization by its unique code from the DB."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Organization retrieved successfully"
    )
    @GetMapping("/{code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("code") String organizationCode) {
        var organization = organizationService.getOrganizationByCode(organizationCode);

        return ResponseEntity.ok(organization);
    }
}
