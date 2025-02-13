package com.propertyservice.property_service.repository.property;

import com.propertyservice.property_service.domain.property.Property;
import com.propertyservice.property_service.domain.property.PropertyPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyPhotoRepository extends JpaRepository<PropertyPhoto, Long> {
    List<PropertyPhoto> findByProperty(Property property);
}
