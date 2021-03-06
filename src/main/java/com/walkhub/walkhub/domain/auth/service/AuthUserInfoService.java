package com.walkhub.walkhub.domain.auth.service;

import com.walkhub.walkhub.domain.auth.presentation.dto.response.AuthUserInfoResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class AuthUserInfoService {

    private final UserFacade userFacade;

    public AuthUserInfoResponse execute() {

        User user = userFacade.getCurrentUser();

        return AuthUserInfoResponse.builder()
                .accountId(user.getAccountId())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
