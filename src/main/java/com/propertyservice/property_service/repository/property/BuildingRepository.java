package com.propertyservice.property_service.repository.property;

import com.propertyservice.property_service.domain.property.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {

}
