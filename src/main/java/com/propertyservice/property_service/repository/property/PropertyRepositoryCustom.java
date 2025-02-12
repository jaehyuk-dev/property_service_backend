package com.propertyservice.property_service.repository.property;

import com.propertyservice.property_service.domain.property.Property;
import com.propertyservice.property_service.dto.property.PropertySearchCondition;
import com.propertyservice.property_service.dto.property.PropertySummaryResponse;

import java.util.List;

public interface PropertyRepositoryCustom {
    List<Property> searchPropertySummaryList(PropertySearchCondition condition, long officeId);
}
