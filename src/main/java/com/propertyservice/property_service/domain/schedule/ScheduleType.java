package com.propertyservice.property_service.domain.schedule;

import lombok.Getter;

@Getter
public enum ScheduleType {
    CONSULTING(41, "상담"),
    CONTRACT_SCHEDULED(42, "계약"),
    CONTRACT_COMPLETED(43, "입주");

    private final int value;
    private final String label;

    ScheduleType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static ScheduleType fromValue(int value) {
        for (ScheduleType type : ScheduleType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid customer status value: " + value);
    }
}

