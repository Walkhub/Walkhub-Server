package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.presentation.dto.request.TeacherCodeRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.TokenResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.exception.InvalidVerificationCodeException;
import com.walkhub.walkhub.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ConfirmTeacherCodeService {

    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse execute(TeacherCodeRequest request) {
        User user = userFacade.getCurrentUser();

        if (!user.getSchool().getAuthCode().equals(request.getCode())) {
            throw InvalidVerificationCodeException.EXCEPTION;
        }
        user.setAuthorityTeacher();

        String accessToken = jwtTokenProvider.generateAccessToken(user.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getAccountId());

        return new TokenResponse(accessToken, refreshToken);
    }
}
