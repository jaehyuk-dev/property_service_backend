package com.propertyservice.property_service.dto.property;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingRegisterRequest {
    private String buildingName;
    @NotNull
    @NotBlank
    private String zonecode;
    @NotNull
    @NotBlank
    private String buildingAddress;
    @NotNull
    @NotBlank
    private String jibunAddress;

    private Short parkSpace;
    @NotNull
    @NotBlank
    private String floorCount;
    private String mainDoorDirection;
    private Short completionYear;

    @NotNull
    private int buildingTypeCode;
    private Short elevatorCount;

    private boolean hasIllegal;
    private String commonPassword;
}
