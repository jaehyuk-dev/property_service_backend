package com.propertyservice.property_service.dto.property;

import com.propertyservice.property_service.domain.property.enums.BuildingType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BuildingDetailResponse {
    private long buildingId;
    private String picUserName;

    private String buildingName;
    private String zoneCode;
    private String buildingAddress;
    private String jibunAddress;
    private Short parkingSpace;
    private String floorcount;
    private String mainDoorDirection;
    private Short completionYear;
    private String buildingType; // ✅ 31(주거용), 32(비주거용)
    private Short elevatorCount;
    private Boolean hasIllegal = false;
    private String commonPassword;

    private List<BuildingRemarkDto> buildingRemarkList;
}
