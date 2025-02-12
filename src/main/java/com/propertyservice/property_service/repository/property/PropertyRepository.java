package com.propertyservice.property_service.repository.property;

import com.propertyservice.property_service.domain.property.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long>, PropertyRepositoryCustom {
}
