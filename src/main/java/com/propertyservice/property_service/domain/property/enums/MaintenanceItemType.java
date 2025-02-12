package com.propertyservice.property_service.domain.property.enums;

import lombok.Getter;

@Getter
public enum MaintenanceItemType {
    WATER(71, "수도"),
    ELECTRICITY(72, "전기"),
    INTERNET(73, "인터넷"),
    HEATING(74, "난방");

    private final int value;
    private final String label;

    MaintenanceItemType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static MaintenanceItemType fromValue(int value) {
        for (MaintenanceItemType type : MaintenanceItemType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid MaintenanceItemType value: " + value);
    }
}
