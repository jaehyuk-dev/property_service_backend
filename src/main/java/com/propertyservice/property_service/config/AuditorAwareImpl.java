package com.propertyservice.property_service.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Configurable
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    @Nullable
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty(); // 인증되지 않은 경우
        }

        Object principal = authentication.getPrincipal();

        // UserDetails 타입이면 사용자 이름 반환
        if (principal instanceof UserDetails) {
            return Optional.of(((UserDetails) principal).getUsername());
        }

        // 인증 정보가 문자열로 저장된 경우
        return Optional.of(principal.toString());
    }
}