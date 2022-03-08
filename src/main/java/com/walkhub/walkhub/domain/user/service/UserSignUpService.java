package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.auth.presentation.dto.response.UserTokenResponse;
import com.walkhub.walkhub.domain.badge.domain.Badge;
import com.walkhub.walkhub.domain.badge.domain.repository.BadgeRepository;
import com.walkhub.walkhub.domain.calorielevel.domain.CalorieLevel;
import com.walkhub.walkhub.domain.calorielevel.domain.repository.CalorieLevelRepository;
import com.walkhub.walkhub.domain.calorielevel.exception.CalorieLevelNotFoundException;
import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.school.domain.repository.SchoolRepository;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.UserAuthCode;
import com.walkhub.walkhub.domain.user.domain.repository.UserAuthCodeRepository;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.domain.type.HealthInfo;
import com.walkhub.walkhub.domain.user.exception.DefaultTitleBadgeNotFound;
import com.walkhub.walkhub.domain.user.exception.SchoolNotFoundException;
import com.walkhub.walkhub.domain.user.exception.UnauthorizedUserAuthCodeException;
import com.walkhub.walkhub.domain.user.exception.UserAuthCodeNotFoundException;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.request.UserSignUpRequest;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.security.jwt.JwtProperties;
import com.walkhub.walkhub.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@WalkhubService
public class UserSignUpService {

    private final UserAuthCodeRepository userAuthCodeRepository;
    private final UserFacade userFacade;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;
    private final BadgeRepository badgeRepository;
    private final CalorieLevelRepository calorieLevelRepository;

    @Transactional
    public UserTokenResponse execute(UserSignUpRequest request) {
        UserAuthCode code = userAuthCodeRepository.findById(request.getPhoneNumber())
                .orElseThrow(() -> UserAuthCodeNotFoundException.EXCEPTION);

        if (!code.getCode().equals(request.getAuthCode()))
            throw UnauthorizedUserAuthCodeException.EXCEPTION;

        userFacade.checkUserExists(request.getAccountId());

        Badge defaultTitleBadge = badgeRepository.findById(1L)
                .orElseThrow(() -> DefaultTitleBadgeNotFound.EXCEPTION);

        School school = schoolRepository.findById(request.getSchoolId())
                .orElseThrow(() -> SchoolNotFoundException.EXCEPTION);

        CalorieLevel calorieLevel = calorieLevelRepository.findByLevel(1)
            .orElseThrow(() -> CalorieLevelNotFoundException.EXCEPTION);

        User user = User.builder()
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
                .badge(defaultTitleBadge)
                .calorieLevel(calorieLevel)
                .build();
        userRepository.save(user);

        school.addUserCount();

        HealthInfo healthInfo = user.getHealthInfo();
        String accessToken = jwtTokenProvider.generateAccessToken(request.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(request.getAccountId());

        return UserTokenResponse.builder()
                .accessToken(accessToken)
                .expiredAt(ZonedDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(refreshToken)
                .authority(user.getAuthority())
                .height(healthInfo.getHeight())
                .weight(healthInfo.getWeight())
                .sex(user.getSex())
                .build();
    }
}
