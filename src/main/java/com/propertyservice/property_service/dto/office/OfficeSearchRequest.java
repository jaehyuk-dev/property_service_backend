package com.propertyservice.property_service.dto.office;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeSearchRequest {
    @NotNull
    @NotBlank
    @Schema(description = "중개사무소 코드", example = "CD-1234-1234")
    private String officeCode;
}
