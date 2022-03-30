package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.response.UserAuthInfoResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryUserAuthInfoService {

    private final UserFacade userFacade;

    public UserAuthInfoResponse execute() {
        User user = userFacade.getCurrentUser();

        return UserAuthInfoResponse.builder()
                .accountId(user.getAccountId())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

}
