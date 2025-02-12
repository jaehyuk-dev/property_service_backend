package com.propertyservice.property_service.repository.schedule;

import com.propertyservice.property_service.domain.client.Client;
import com.propertyservice.property_service.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByClientOrderByDateDesc(Client client);
}
