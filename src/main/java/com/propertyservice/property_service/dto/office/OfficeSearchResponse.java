package com.propertyservice.property_service.dto.office;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OfficeSearchResponse {

    @Schema(description = "중개사무소 이름", example = "중개사무소")
    private String officeName;

    @Schema(description = "중개사무소 대표자 명", example = "대표자")
    private String presidentName;
}
