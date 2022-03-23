package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.presentation.dto.request.TeacherCodeRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.TokenResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import com.walkhub.walkhub.global.exception.VerificationCodeNotFoundException;
import com.walkhub.walkhub.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class ConfirmTeacherCodeService {

    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse execute(TeacherCodeRequest request) {
        User user = userFacade.getCurrentUser();

        if (!user.getSchool().getAuthCode().equals(request.getCode())) {
            throw VerificationCodeNotFoundException.EXCEPTION;
        }
        user.setAuthorityTeacher();
        user.setSectionNull();

        String accessToken = jwtTokenProvider.generateAccessToken(user.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getAccountId());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
