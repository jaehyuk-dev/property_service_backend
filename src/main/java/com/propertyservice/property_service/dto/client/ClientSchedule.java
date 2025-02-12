package com.propertyservice.property_service.dto.client;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientSchedule {
    private long scheduleId;
    private String scheduleName;
}
