package com.propertyservice.property_service.repository.client;

import com.propertyservice.property_service.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
