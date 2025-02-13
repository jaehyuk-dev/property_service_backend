package com.propertyservice.property_service.repository.property;

import com.propertyservice.property_service.domain.property.PropertyOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyOptionRepository extends JpaRepository<PropertyOption, Long> {
}
