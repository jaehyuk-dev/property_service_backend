package com.propertyservice.property_service.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRemarkRequest {
    @NotNull
    private long clientId;
    @NotNull
    @NotBlank
    private String remark;
}
