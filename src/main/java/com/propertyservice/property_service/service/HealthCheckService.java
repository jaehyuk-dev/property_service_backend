package com.propertyservice.property_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HealthCheckService {

    public boolean checkApplicationHealth() {
        // 간단한 헬스 체크 로직(예: 항상 true 반환)
        // 실제로는 DB 연결, 캐시 상태, 외부 서비스 상태 등을 점검
        return true;
    }
}
