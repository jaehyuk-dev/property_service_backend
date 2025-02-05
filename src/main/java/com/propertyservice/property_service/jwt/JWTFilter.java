package com.propertyservice.property_service.jwt;

import com.propertyservice.property_service.domain.office.OfficeUser;
import com.propertyservice.property_service.domain.office.Role;
import com.propertyservice.property_service.dto.office.CustomUserDetails;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        // 헤더 값 로그 확인
        logger.info("Authorization Header: " + authorizationHeader);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            logger.warn("JWT Token is missing or malformed");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring(7); // "Bearer " 제거

        try {
            if (jwtUtil.isExpired(token)) {
                logger.warn("JWT Token is expired");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\": \"JWT expired\"}");
                return;
            }

            // JWT가 유효하면 SecurityContext에 저장
            String username = jwtUtil.getUsername(token);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, List.of());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token format", e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Invalid JWT format\"}");
            return;
        } catch (Exception e) {
            logger.error("JWT authentication failed", e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Invalid token\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
