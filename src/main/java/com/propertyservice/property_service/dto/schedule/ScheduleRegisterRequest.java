package com.propertyservice.property_service.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleRegisterRequest {

    @NotNull
    private long scheduleClientId;
    @NotNull
    private LocalDateTime scheduleDateTime;
    @NotNull
    @NotBlank
    private int scheduleTypeCode;
    private String scheduleRemark;
}
