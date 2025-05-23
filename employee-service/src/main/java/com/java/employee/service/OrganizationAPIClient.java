package com.java.employee.service;

import com.java.employee.config.FeignConfig;
import com.java.employee.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE", configuration = FeignConfig.class)
public interface OrganizationAPIClient {

    // This is a Feign client that communicates with the Organization Service to fetch organization details
    @GetMapping("organizations/{code}")
    OrganizationDto getOrganizationByCode(@PathVariable("code") String organizationCode);
}
