package com.propertyservice.property_service.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.Nullable;

import java.util.Optional;

@Configurable
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    @Nullable
    public Optional<String> getCurrentAuditor() {
        return Optional.of("system");
    }
}