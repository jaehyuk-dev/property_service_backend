package com.propertyservice.property_service.domain.property.enums;

import lombok.Getter;

@Getter
public enum BuildingType {
    RESIDENTIAL(51, "주거용"),
    NON_RESIDENTIAL(52, "비주거용");

    private final int value;
    private final String label;

    BuildingType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static BuildingType fromValue(int value) {
        for (BuildingType type : BuildingType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid BuildingType value: " + value);
    }
}
