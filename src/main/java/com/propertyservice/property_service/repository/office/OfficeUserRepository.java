package com.propertyservice.property_service.repository.office;

import com.propertyservice.property_service.domain.office.Office;
import com.propertyservice.property_service.domain.office.OfficeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficeUserRepository extends JpaRepository<OfficeUser, Long> {
    boolean existsByEmail(String email);
    boolean existsByOffice(Office office);
    Optional<OfficeUser> findByEmail(String email);
    long countByOffice(Office office);
}
