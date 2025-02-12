package com.propertyservice.property_service.repository.client;

import com.propertyservice.property_service.domain.client.Client;
import com.propertyservice.property_service.domain.client.ClientExpectedTransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientExpectedTransactionTypeRepository extends JpaRepository<ClientExpectedTransactionType, Long> {
    List<ClientExpectedTransactionType> findByClient(Client client);
}
