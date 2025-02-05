package com.propertyservice.property_service.jwt;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JWTUtil {
    private final SecretKey secretKey;

    public JWTUtil(@Value("${jwt.secret}") String secret) {
        log.info("Initializing JWTUtil with secret key");
        if (secret == null || secret.trim().isEmpty()) {
            throw new IllegalArgumentException("JWT secret key cannot be null or empty");
        }

        // HMAC-SHA256 알고리즘용 키 생성
        this.secretKey = Jwts.SIG.HS256.key().build();
        log.info("JWTUtil initialized successfully with key algorithm: {}", secretKey.getAlgorithm());
    }

    // 토큰 jwt 토큰 생성
    public String createJwt(String username, String role, Long expiredMs) {
        log.info("Creating JWT + " + secretKey);
        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    // 유저 이름 값 가져오기
    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("username", String.class);
    }

    // 유저 권한 가져오기
    public String getRole(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    // 토큰 유효기간 확인
    public Boolean isExpired(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }
}