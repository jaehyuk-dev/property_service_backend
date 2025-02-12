package com.propertyservice.property_service.dto.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleCompleteRequest {
    @NotNull
    private long scheduleId;
    @NotNull
    private boolean complete;
}
