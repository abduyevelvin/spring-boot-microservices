package com.java.employee;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@OpenAPIDefinition(
        info = @Info(
                title = "Employee Service API",
                version = "1.0",
                description = "API for managing employees",
                contact = @Contact(
                        name = "Support Team",
                        email = "test@test.com",
                        url = "https://example.com/support"
                ),
                license = @License(
                        name = "Apache License 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Employee Service Documentation",
                url = "https://example.com/employee-service-docs"
        )
)
@SpringBootApplication
@EnableFeignClients
public class EmployeeServiceApplication {

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

//    @Bean
//    public WebClient webClient() {
//        return WebClient.builder()
//                        .build();
//    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }

}
