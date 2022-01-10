package com.walkhub.walkhub.domain.auth.service;

import com.walkhub.walkhub.domain.auth.domain.RefreshToken;
import com.walkhub.walkhub.domain.auth.domain.repository.RefreshTokenRepository;
import com.walkhub.walkhub.domain.auth.prsentation.dto.request.SignInRequest;
import com.walkhub.walkhub.domain.auth.prsentation.dto.response.UserTokenResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.exception.UserNotFoundException;
import com.walkhub.walkhub.global.security.jwt.JwtProperties;
import com.walkhub.walkhub.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserSignInService {

    private final UserRepository userRepository;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserTokenResponse execute(SignInRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        String accessToken = jwtTokenProvider.generateAccessToken(user.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getAccountId());


        refreshTokenRepository.save(
                RefreshToken.builder()
                        .accountId(user.getAccountId())
                        .token(refreshToken)
                        .build()
        );

        return UserTokenResponse.builder()
                .accessToken(accessToken)
                .expiredAt(LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(refreshToken)
                .build();
    }

}
