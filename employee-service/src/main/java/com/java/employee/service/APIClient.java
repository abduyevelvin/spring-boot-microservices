package com.java.employee.service;

import com.java.employee.config.FeignConfig;
import com.java.employee.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE", configuration = FeignConfig.class)
public interface APIClient {

    // This is a Feign client that communicates with the Department Service to fetch department details
    @GetMapping("departments/{code}")
    DepartmentDto getDepartmentByCode(@PathVariable("code") String departmentCode);
}
