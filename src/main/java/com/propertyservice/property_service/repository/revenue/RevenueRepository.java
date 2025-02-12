package com.propertyservice.property_service.repository.revenue;

import com.propertyservice.property_service.domain.revenue.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {
}
