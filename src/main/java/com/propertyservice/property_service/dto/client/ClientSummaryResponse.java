package com.propertyservice.property_service.dto.client;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ClientSummaryResponse {
    private long clientId;
    private String clientName;
    private String clientState;
    private String clientPhoneNumber;
    private String picManager;
    private String clientSource;
    private LocalDate moveInDate;

    @QueryProjection
    public ClientSummaryResponse(long clientId, String clientName, String clientState, String clientPhoneNumber, String picManager, String clientSource, LocalDate moveInDate) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientState = clientState;
        this.clientPhoneNumber = clientPhoneNumber;
        this.picManager = picManager;
        this.clientSource = clientSource;
        this.moveInDate = moveInDate;
    }
}
