package com.propertyservice.property_service.domain.property.enums;

import lombok.Getter;

@Getter
public enum PropertyOptionType {
    AIR_CONDITIONER(41, "에어컨"),
    WASHING_MACHINE(42, "세탁기"),
    REFRIGERATOR(43, "냉장고"),
    GAS_RANGE(44, "가스레인지");

    private final int value;
    private final String label;

    PropertyOptionType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static PropertyOptionType fromValue(int value) {
        for (PropertyOptionType type : PropertyOptionType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid PropertyOptionType value: " + value);
    }
}
