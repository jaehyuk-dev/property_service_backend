package com.propertyservice.property_service.dto.client;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ClientStatusUpdateRequest {
    private long clientId;
    private int newStatusCode;

    private long showingPropertyId;
    private long propertyId;
    private Long commissionFee;
    private LocalDate moveInDate;
    private LocalDate moveOutDate;
}
