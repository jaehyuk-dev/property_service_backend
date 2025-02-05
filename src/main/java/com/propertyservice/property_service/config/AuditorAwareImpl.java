package com.propertyservice.property_service.config;

import com.propertyservice.property_service.dto.auth.CustomUserDetails;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configurable
public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    @Nullable
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증되지 않은 경우
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();

        // CustomUserDetails로 캐스팅
        if (principal instanceof CustomUserDetails customUserDetails) {
            return Optional.of(customUserDetails.getUserEntity().getUserId());
        }

        // principal이 예상 타입이 아닌 경우
        return Optional.empty();
    }
}