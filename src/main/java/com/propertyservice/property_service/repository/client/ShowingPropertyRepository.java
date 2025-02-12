package com.propertyservice.property_service.repository.client;

import com.propertyservice.property_service.domain.client.Client;
import com.propertyservice.property_service.domain.client.ShowingProperty;
import com.propertyservice.property_service.domain.property.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowingPropertyRepository extends JpaRepository<ShowingProperty, Long> {
    List<ShowingProperty> findByClient(Client client);

    List<ShowingProperty> findByPropertyAndProperty(Property property, Property property1);
}
