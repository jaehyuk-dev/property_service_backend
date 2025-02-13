package com.propertyservice.property_service.repository.property;

import com.propertyservice.property_service.dto.property.BuildingSearchCondition;
import com.propertyservice.property_service.dto.property.BuildingSummaryResponse;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingSummaryResponse> searchBuildingSummaryList(BuildingSearchCondition condition, long officeId);
}
