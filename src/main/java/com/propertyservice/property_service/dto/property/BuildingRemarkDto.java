package com.propertyservice.property_service.dto.property;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BuildingRemarkDto {
    private long remarkId;
    private String remark;
    private LocalDateTime createdAt;
    private String createdBy;
}
