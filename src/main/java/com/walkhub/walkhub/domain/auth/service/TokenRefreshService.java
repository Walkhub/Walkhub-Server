package com.walkhub.walkhub.domain.auth.service;

import com.walkhub.walkhub.domain.auth.domain.RefreshToken;
import com.walkhub.walkhub.domain.auth.domain.repository.RefreshTokenRepository;
import com.walkhub.walkhub.domain.auth.exception.RefreshTokenNotFoundException;
import com.walkhub.walkhub.domain.auth.presentation.dto.response.UserAccessTokenResponse;
import com.walkhub.walkhub.global.security.jwt.JwtProperties;
import com.walkhub.walkhub.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class TokenRefreshService {

    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserAccessTokenResponse execute(String refreshToken) {
        RefreshToken redisRefreshToken = refreshTokenRepository.findByToken(jwtTokenProvider.parseToken(refreshToken))
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        redisRefreshToken.updateExp(jwtProperties.getRefreshExp());

        String accessToken = jwtTokenProvider.generateAccessToken(redisRefreshToken.getAccountId());
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp()));
        return UserAccessTokenResponse.builder()
                .accessToken(accessToken)
                .expiredAt(LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .build();
    }

}
