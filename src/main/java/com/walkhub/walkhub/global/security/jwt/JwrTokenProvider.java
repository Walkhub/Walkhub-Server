package com.walkhub.walkhub.global.security.jwt;

import com.walkhub.walkhub.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwrTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;

    public String generateAccessToken(String id) {
        return Jwts.builder()
    }
}
