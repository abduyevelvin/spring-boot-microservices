package com.java.department.service.impl;

import com.java.department.dto.DepartmentDto;
import com.java.department.exception.ResourceNotFoundException;
import com.java.department.repository.DepartmentRepository;
import com.java.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.java.department.mapper.DepartmentMapper.DEPARTMENT_MAPPER;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        // Convert DTO to Entity
        var departmentEntity = DEPARTMENT_MAPPER.toDepartmentEntity(departmentDto);

        var savedEntity = departmentRepository.save(departmentEntity);

        return DEPARTMENT_MAPPER.toDepartmentDto(savedEntity);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        var departmentEntity = departmentRepository.findByDepartmentCode(departmentCode)
                                                   .orElseThrow(() -> new ResourceNotFoundException(
                                                           "Department",
                                                           "id",
                                                           departmentCode
                                                   ));

        return DEPARTMENT_MAPPER.toDepartmentDto(departmentEntity);
    }
}
