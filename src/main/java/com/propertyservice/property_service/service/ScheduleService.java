package com.propertyservice.property_service.service;

import com.propertyservice.property_service.domain.schedule.Schedule;
import com.propertyservice.property_service.domain.schedule.ScheduleType;
import com.propertyservice.property_service.dto.schedule.ScheduleCompleteRequest;
import com.propertyservice.property_service.dto.schedule.ScheduleRegisterRequest;
import com.propertyservice.property_service.error.ErrorCode;
import com.propertyservice.property_service.error.exception.BusinessException;
import com.propertyservice.property_service.repository.client.ClientRepository;
import com.propertyservice.property_service.repository.schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {

    private final ClientRepository clientRepository;
    private ScheduleRepository scheduleRepository;
    private final AuthService authService;

    @Transactional
    public void createSchedule(ScheduleRegisterRequest request) {
        scheduleRepository.save(
                Schedule.builder()
                        .picUser(authService.getCurrentUserEntity())
                        .date(request.getScheduleDateTime())
                        .client(clientRepository.findById(request.getScheduleClientId()).orElseThrow(
                                () -> new BusinessException(ErrorCode.CLIENT_NOT_FOUND)
                        ))
                        .scheduleType(ScheduleType.fromValue(request.getScheduleTypeCode()))
                        .remark(request.getScheduleRemark())
                        .isCompleted(false)
                        .build()
        );
    }

    @Transactional
    public void removeSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    @Transactional
    public void updateSchedule(ScheduleCompleteRequest request) {
        scheduleRepository.findById(request.getScheduleId()).orElseThrow(
                () -> new BusinessException(ErrorCode.SCHEDULE_NOT_FOUND)
        ).updateComplete(request.isComplete());
    }
}
