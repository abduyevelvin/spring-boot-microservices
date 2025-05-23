package com.example.organization.controller;

import com.example.organization.dto.OrganizationDto;
import com.example.organization.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    // Build the REST API for saving organization
    @PostMapping
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody @Valid OrganizationDto organizationDto) {
        var organization = organizationService.saveOrganization(organizationDto);

        return ResponseEntity.status(CREATED)
                             .body(organization);
    }

    // Build the REST API for getting organization by code
    @GetMapping("/{code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("code") String organizationCode) {
        var organization = organizationService.getOrganizationByCode(organizationCode);

        return ResponseEntity.ok(organization);
    }
}
