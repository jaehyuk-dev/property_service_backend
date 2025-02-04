package com.propertyservice.property_service.repository.office;

import com.propertyservice.property_service.domain.office.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Long> {
}
