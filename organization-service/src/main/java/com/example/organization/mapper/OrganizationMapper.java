package com.example.organization.mapper;

import com.example.organization.dto.OrganizationDto;
import com.example.organization.entity.OrganizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {

    OrganizationMapper ORGANIZATION_MAPPER = Mappers.getMapper(OrganizationMapper.class);

    @Mapping(source = "id", target = "organizationId")
    @Mapping(source = "createdAt", target = "creationDate")
    OrganizationDto toOrganizationDto(OrganizationEntity organizationEntity);

    @Mapping(source = "organizationId", target = "id")
    @Mapping(source = "creationDate", target = "createdAt")
    OrganizationEntity toOrganizationEntity(OrganizationDto organizationDto);
}
