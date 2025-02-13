package com.propertyservice.property_service.repository.property;

import com.propertyservice.property_service.domain.property.MaintenanceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceItemRepository extends JpaRepository<MaintenanceItem, Long> {
}
