package com.propertyservice.property_service.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.propertyservice.property_service.dto.office.CustomUserDetails;
import com.propertyservice.property_service.dto.office.LoginRequest;
import com.propertyservice.property_service.error.ErrorCode;
import com.propertyservice.property_service.error.exception.BusinessException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/auth/login"); // 필터가 로그인 요청을 가로채도록 설정
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        logger.info("Try to login");

        // 1. 요청 방식 확인 (POST만 허용)
        if (!request.getMethod().equals("POST")) {
            logger.warn("Request Method: " + request.getMethod());
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        try {
            // 2. JSON 요청 본문에서 email, password 읽기
            ObjectMapper objectMapper = new ObjectMapper();
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class); // JSON -> DTO 변환

            logger.info("Login Request Email: " + loginRequest.getEmail());

            String email = loginRequest.getEmail(); // DTO에서 email 추출
            String password = loginRequest.getPassword(); // DTO에서 password 추출

            // 3. email 또는 password가 없으면 예외 발생
            if (email == null || password == null) {
                throw new AuthenticationServiceException("로그인 입력 값이 비어있습니다.");
            }

            // 4. UsernamePasswordAuthenticationToken 생성 및 AuthenticationManager에 전달
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password, null);

            logger.info("Authenticating user : " + authToken.getName());

            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read login request", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        logger.info("Successfully logged in as " + authentication.getName());

        // 로그인한 특정 유저 확인
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        // 유저 name 확인
        String username = customUserDetails.getUsername();

        // 유저 role 확인
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        // jwt token 생성
        String token = jwtUtil.createJwt(username, role, 60*60*10L);

        // header 응답
        response.addHeader("Authorization", "Bearer " + token);
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        logger.warn(failed.getMessage());
        throw new BusinessException(ErrorCode.INVALID_USER_CREDENTIALS);
    }

}
