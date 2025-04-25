package com.java.department.mapper;

import com.java.department.dto.DepartmentDto;
import com.java.department.entity.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper DEPARTMENT_MAPPER = Mappers.getMapper(DepartmentMapper.class);

    @Mapping(source = "id", target = "departmentId")
    DepartmentDto toDepartmentDto(DepartmentEntity departmentEntity);

    @Mapping(source = "departmentId", target = "id")
    DepartmentEntity toDepartmentEntity(DepartmentDto departmentDto);
}
