package com.propertyservice.property_service.dto.common;

import org.springframework.http.HttpStatus;

public class SuccessResponseDto<T> extends ApiResponseDto<T> {

    public SuccessResponseDto(T data) {
        super(HttpStatus.OK.value(), "success", data);
    }
}
