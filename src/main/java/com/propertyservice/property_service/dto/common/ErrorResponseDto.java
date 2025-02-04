package com.propertyservice.property_service.dto.common;

import org.springframework.http.HttpStatus;

public class ErrorResponseDto extends ApiResponseDto<Void> {

    public ErrorResponseDto(HttpStatus status, String message) {
        super(status.value(), message, null);
    }
}
