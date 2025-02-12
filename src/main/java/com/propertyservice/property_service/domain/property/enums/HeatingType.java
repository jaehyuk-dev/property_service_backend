package com.propertyservice.property_service.domain.property.enums;

import lombok.Getter;

@Getter
public enum HeatingType {
    INDIVIDUAL(81, "개별"),
    CENTRAL(82, "중앙"),
    NIGHT(83, "심야");

    private final int value;
    private final String label;

    HeatingType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static HeatingType fromValue(int value) {
        for (HeatingType type : HeatingType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid HeatingType value: " + value);
    }
}
