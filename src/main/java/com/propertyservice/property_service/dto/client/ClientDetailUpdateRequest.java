package com.propertyservice.property_service.dto.client;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ClientDetailUpdateRequest {
    private long clientId;
    private String clientName;
    private String clientPhoneNumber;
    private String clientType;
    private String clientSource;
    private List<String> clientExpectedTransactionTypeList;
    private LocalDate clientExpectedMoveInDate;
}
