package com.propertyservice.property_service.domain.common.eums;

import lombok.Getter;

@Getter
public enum TransactionType {
    MONTHLY(61, "월세"),
    JEONSE(62, "전세"),
    SHORTTERM(64, "단기");

    private final int value;
    private final String label;

    TransactionType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static TransactionType fromValue(int value) {
        for (TransactionType type : TransactionType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid gender value: " + value);
    }
}
