package com.propertyservice.property_service.dto.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowingPropertyRegisterRequest {
    private long clientId;
    private long propertyId;
}
