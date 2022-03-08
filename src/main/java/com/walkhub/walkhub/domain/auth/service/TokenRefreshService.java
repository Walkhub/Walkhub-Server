package com.walkhub.walkhub.domain.auth.service;

import com.walkhub.walkhub.domain.auth.domain.RefreshToken;
import com.walkhub.walkhub.domain.auth.domain.repository.RefreshTokenRepository;
import com.walkhub.walkhub.domain.auth.exception.RefreshTokenNotFoundException;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import com.walkhub.walkhub.domain.auth.presentation.dto.response.UserTokenRefreshResponse;
import com.walkhub.walkhub.global.security.jwt.JwtProperties;
import com.walkhub.walkhub.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@WalkhubService
public class TokenRefreshService {

    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserTokenRefreshResponse execute(String refreshToken) {
        RefreshToken redisRefreshToken = refreshTokenRepository.findByToken(jwtTokenProvider.parseToken(refreshToken))
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String newRefreshToken = jwtTokenProvider.generateRefreshToken(redisRefreshToken.getAccountId());
        redisRefreshToken.updateToken(newRefreshToken);

        String accessToken = jwtTokenProvider.generateAccessToken(redisRefreshToken.getAccountId());
        return UserTokenRefreshResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .expiredAt(ZonedDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .build();
    }

}
