package com.propertyservice.property_service.repository.client;

import com.propertyservice.property_service.domain.client.Client;
import com.propertyservice.property_service.domain.client.ClientRemark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRemarkRepository extends JpaRepository<ClientRemark, Long> {
    List<ClientRemark> findByClient(Client client);
}
