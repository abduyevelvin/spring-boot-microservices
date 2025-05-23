package com.example.organization.service.impl;

import com.example.organization.dto.OrganizationDto;
import com.example.organization.exception.OrganizationAlreadyExistsException;
import com.example.organization.exception.ResourceNotFoundException;
import com.example.organization.repository.OrganizationRepository;
import com.example.organization.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.organization.mapper.OrganizationMapper.ORGANIZATION_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        var existingOrganization = organizationRepository.findByOrganizationCode(organizationDto.organizationCode());

        if (existingOrganization.isPresent()) {
            log.warn("Organization already exists with the code: {}", organizationDto.organizationCode());

            throw new OrganizationAlreadyExistsException(
                    "Organization already exists with the code: " + organizationDto.organizationCode());
        }

        // Convert DTO to Entity
        var organizationEntity = ORGANIZATION_MAPPER.toOrganizationEntity(organizationDto);

        return ORGANIZATION_MAPPER.toOrganizationDto(organizationRepository.save(organizationEntity));
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        var existingOrganization = organizationRepository.findByOrganizationCode(organizationCode)
                                                         .orElseThrow(() -> {
                                                                     log.warn(
                                                                             "Organization not found with the code: {}",
                                                                             organizationCode
                                                                     );
                                                                     return new ResourceNotFoundException(
                                                                             "Organization",
                                                                             "code",
                                                                             organizationCode
                                                                     );
                                                                 }
                                                         );

        return ORGANIZATION_MAPPER.toOrganizationDto(existingOrganization);
    }
}
