package com.propertyservice.property_service.dto.property;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class BuildingSummaryResponse {
    private final Long buildingId;
    private final String buildingName;
    private final String buildingAddress;
    private final String photoUrl;

    @QueryProjection
    public BuildingSummaryResponse(Long buildingId, String buildingName, String buildingAddress, String photoUrl) {
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.buildingAddress = buildingAddress;
        this.photoUrl = photoUrl;
    }
}
