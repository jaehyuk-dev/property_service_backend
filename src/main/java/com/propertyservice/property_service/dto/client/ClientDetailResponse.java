package com.propertyservice.property_service.dto.client;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class ClientDetailResponse {
    private long clientId;
    private String clientName;
    private String picUserName;
    private String clientStatus;
    private String clientSource;
    private String clientType;
    private LocalDate clientExpectedMoveInDate;

    private List<ClientScheduleDto> clientScheduleList;
    private List<ShowingPropertyDto> showingPropertyList;
    private List<ClientRemarkDto> clientRemarkList;
}
