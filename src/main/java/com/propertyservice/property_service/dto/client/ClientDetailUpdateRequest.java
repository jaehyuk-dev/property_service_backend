package com.propertyservice.property_service.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ClientDetailUpdateRequest {
    @NotNull
    private long clientId;
    @NotNull
    @NotBlank
    private String clientName;
    @NotNull
    @NotBlank
    private String clientPhoneNumber;
    @NotNull
    @NotBlank
    private String clientType;
    @NotNull
    @NotBlank
    private String clientSource;

    private List<String> clientExpectedTransactionTypeList;
    @NotNull
    private LocalDate clientExpectedMoveInDate;
}
