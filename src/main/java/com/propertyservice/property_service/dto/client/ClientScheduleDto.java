package com.propertyservice.property_service.dto.client;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientScheduleDto {
    private long scheduleId;
    private String picUser;
    private String date;
    private String scheduleType;
    private String remark;
    private boolean isCompleted;
}
