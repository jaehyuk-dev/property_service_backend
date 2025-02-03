//package com.propertyservice.property_service.config;
//
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.util.StringUtils;
//
//import java.util.Optional;
//
//@Configurable
//public class AuditorAwareImpl implements AuditorAware<String> {
//
//    @Autowired
//    private HttpServletRequest httpServletRequest;
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        String modiedBy = httpServletRequest.getRequestURI();
//        if(!StringUtils.hasText(modiedBy)){
//            modiedBy = "unknown";
//        }
//        return Optional.of(modiedBy);
//    }
//}