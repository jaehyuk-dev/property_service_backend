package com.propertyservice.property_service.domain.client;

import lombok.Getter;

@Getter
public enum ClientStatus {
    CONSULTING(21, "상담 중"),
    CONTRACT_SCHEDULED(22, "계약 예정"),
    MOVING_IN(23, "입주 예정"),
    CONTRACT_COMPLETED(24, "계약 완료");

    private final int value;
    private final String label;

    ClientStatus(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static ClientStatus fromValue(int value) {
        for (ClientStatus status : ClientStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid customer status value: " + value);
    }
}
