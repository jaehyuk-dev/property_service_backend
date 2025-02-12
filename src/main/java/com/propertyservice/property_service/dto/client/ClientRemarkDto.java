package com.propertyservice.property_service.dto.client;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ClientRemarkDto {
    private long remarkId;
    private String remark;
    private String createdBy;
    private String createdTime;
}
