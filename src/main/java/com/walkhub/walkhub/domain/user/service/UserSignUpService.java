package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.auth.presentation.dto.response.UserTokenResponse;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.domain.repository.SchoolRepository;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.UserAuthCode;
import com.walkhub.walkhub.domain.user.domain.repository.UserAuthCodeRepository;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.exception.SchoolNotFoundException;
import com.walkhub.walkhub.domain.user.exception.UnauthorizedUserAuthCodeException;
import com.walkhub.walkhub.domain.user.exception.UserAuthCodeNotFoundException;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UserSignUpRequest;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.security.jwt.JwtProperties;
import com.walkhub.walkhub.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserSignUpService {

    private final UserAuthCodeRepository userAuthCodeRepository;
    private final UserFacade userFacade;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    public UserTokenResponse execute(UserSignUpRequest request) {
        UserAuthCode code = userAuthCodeRepository.findById(request.getPhoneNumber())
                .orElseThrow(() -> UserAuthCodeNotFoundException.EXCEPTION);

        if (!code.getCode().equals(request.getAuthCode()))
            throw UnauthorizedUserAuthCodeException.EXCEPTION;

        userFacade.checkUserExists(request.getAccountId());

        School school = schoolRepository.findById(request.getSchoolId())
                .orElseThrow(() -> SchoolNotFoundException.EXCEPTION);

        userRepository.save(User.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .authority(Authority.USER)
                .name(request.getName())
                .school(school)
                .height(request.getHeight())
                .weight(request.getWeight())
                .sex(request.getSex())
                .isMeasuring(false)
                .build());

        String accessToken = jwtTokenProvider.generateAccessToken(request.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(request.getAccountId());

        return UserTokenResponse.builder()
                .accessToken(accessToken)
                .expiredAt(LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(refreshToken)
                .build();
    }
}
