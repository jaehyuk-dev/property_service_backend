package com.propertyservice.property_service.repository.client;


import com.propertyservice.property_service.dto.client.ClientSearchCondition;
import com.propertyservice.property_service.dto.client.ClientSummaryResponse;

import java.util.List;

public interface ClientRepositoryCustom {
    List<ClientSummaryResponse> searchClientSummaryList(ClientSearchCondition condition,  Long officeId);
}
