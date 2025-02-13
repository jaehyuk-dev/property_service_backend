package com.propertyservice.property_service.dto.property;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PropertyResponse {
    private long propertyId;
    private String buildingName;
    private String propertyAddress;
    private String photoUrl;
    private String propertyTransactionType;
    private String propertyPrice;

    @Builder
    @QueryProjection
    public PropertyResponse(long propertyId, String buildingName, String propertyAddress, String photoUrl, String propertyTransactionType, String propertyPrice) {
        this.propertyId = propertyId;
        this.buildingName = buildingName;
        this.propertyAddress = propertyAddress;
        this.photoUrl = photoUrl;
        this.propertyTransactionType = propertyTransactionType;
        this.propertyPrice = propertyPrice;
    }
}
