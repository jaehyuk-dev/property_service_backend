package com.propertyservice.property_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Property Service",
                description = "Property Service API 명세서",
                version = "v1",
                contact = @Contact(
                        name = "이재혁",
                        email = "paikbean@gmail.com"
                )
        )
)
@Configuration
public class OpenApiConfig {
}
