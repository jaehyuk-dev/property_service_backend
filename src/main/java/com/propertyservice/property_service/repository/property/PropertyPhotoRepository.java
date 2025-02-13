package com.propertyservice.property_service.repository.property;

import com.propertyservice.property_service.domain.property.PropertyPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyPhotoRepository extends JpaRepository<PropertyPhoto, Long> {

}
