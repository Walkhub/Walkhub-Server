package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.auth.domain.RefreshToken;
import com.walkhub.walkhub.domain.auth.domain.repository.RefreshTokenRepository;
import com.walkhub.walkhub.domain.auth.exception.RefreshTokenNotFoundException;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class LogoutService {

    private final UserFacade userFacade;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();
        user.setDeviceToken(null);

        RefreshToken refreshToken = refreshTokenRepository.findByAccountId(user.getAccountId())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        refreshTokenRepository.delete(refreshToken);
    }

}
