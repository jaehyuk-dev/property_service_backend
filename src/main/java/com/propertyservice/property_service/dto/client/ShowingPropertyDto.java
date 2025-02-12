package com.propertyservice.property_service.dto.client;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShowingPropertyDto {
    private long showingPropertyId;
    private String propertyTransactionType;
    private String propertyPrice;
    private String propertyType;
    private String propertyAddress;
}
