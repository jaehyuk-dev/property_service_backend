package com.propertyservice.property_service.dto.property;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PropertySummaryResponse {
    private long propertyId;
    private String propertyTransactionType;
    private String propertyPrice;
    private String propertyType;
    private String propertyAddress;

    @Builder
    public PropertySummaryResponse(long propertyId, String propertyTransactionType, String propertyPrice, String propertyType, String propertyAddress) {
        this.propertyId = propertyId;
        this.propertyTransactionType = propertyTransactionType;
        this.propertyPrice = propertyPrice;
        this.propertyType = propertyType;
        this.propertyAddress = propertyAddress;
    }
}
