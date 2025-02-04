package com.propertyservice.property_service.repository.office;

import com.propertyservice.property_service.domain.office.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficeRepository extends JpaRepository<Office, Long> {
    boolean existsByOfficeCode(String officeCode);
    Optional<Office> findByOfficeCode(String officeCode);
}
