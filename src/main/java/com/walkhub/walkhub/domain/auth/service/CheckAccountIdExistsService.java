package com.walkhub.walkhub.domain.auth.service;

import com.walkhub.walkhub.domain.auth.presentation.dto.request.CheckAccountIdRequest;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class CheckAccountIdExistsService {

    private final UserFacade userFacade;

    public void execute(CheckAccountIdRequest request) {
        userFacade.checkUserExists(request.getAccountId());
    }
}
