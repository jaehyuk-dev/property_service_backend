package com.propertyservice.property_service.utils;

import com.propertyservice.property_service.dto.auth.CustomUserDetails;
import com.propertyservice.property_service.error.ErrorCode;
import com.propertyservice.property_service.error.exception.BusinessException;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@UtilityClass
public class SecurityUtil {
    // 현재 로그인한 사용자 정보를 Optional로 반환
    public Optional<CustomUserDetails> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails) {
            return Optional.of((CustomUserDetails) principal);
        }

        return Optional.empty();
    }

    // 비밀번호 규칙 : 숫자, 특수문자 포함 및 최소 8자 이상
    public void validatePasswordStrength(String newPassword) {
        String passwordPattern = "^(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        if (!newPassword.matches(passwordPattern)) {
            throw new BusinessException(ErrorCode.WEAK_PASSWORD);
        }
    }
}
