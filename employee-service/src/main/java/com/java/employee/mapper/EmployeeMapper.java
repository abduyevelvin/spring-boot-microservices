package com.java.employee.mapper;

import com.java.employee.dto.EmployeeDto;
import com.java.employee.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper EMPLOYEE_MAPPER = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "id", target = "employeeId")
    EmployeeDto toEmployeeDto(EmployeeEntity employeeEntity);

    @Mapping(source = "employeeId", target = "id")
    EmployeeEntity toEmployeeEntity(EmployeeDto employeeDto);
}
