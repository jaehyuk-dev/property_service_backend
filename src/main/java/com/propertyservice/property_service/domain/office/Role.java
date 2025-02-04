package com.propertyservice.property_service.domain.office;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN(10, "대표"), // 대표자
    USER(20, "직원"); // 일반 사용자

    private final int value; // 권한 값
    private final String label; // 표시용 라벨

    Role(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static Role fromValue(int value) {
        for (Role role : Role.values()) {
            if (role.getValue() == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role value: " + value);
    }

    public static Role fromLabel(String label) {
        for (Role role : Role.values()) {
            if (role.getLabel().equalsIgnoreCase(label)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role label: " + label);
    }
}