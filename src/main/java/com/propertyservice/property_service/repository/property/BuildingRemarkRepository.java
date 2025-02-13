package com.propertyservice.property_service.repository.property;

import com.propertyservice.property_service.domain.property.Building;
import com.propertyservice.property_service.domain.property.BuildingRemark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRemarkRepository extends JpaRepository<BuildingRemark, Long> {
    List<BuildingRemark> findByBuilding(Building building);
}
