package com.propertyservice.property_service.domain.common.eums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE(31, "남성"),
    FEMALE(32, "여성");

    private final int value;
    private final String label;

    Gender(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static Gender fromValue(int value) {
        for (Gender gender : Gender.values()) {
            if (gender.getValue() == value) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid gender value: " + value);
    }
}
