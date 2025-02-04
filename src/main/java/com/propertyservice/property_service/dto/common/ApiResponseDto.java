package com.propertyservice.property_service.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "공통 API 응답 DTO")
public abstract class ApiResponseDto<T> {

    @Schema(description = "HTTP 상태 코드", example = "200")
    private int status;

    @Schema(description = "응답 메시지", example = "success")
    private String message;

    @Schema(description = "응답 데이터")
    private T data;
}
