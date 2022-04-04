package com.walkhub.walkhub.domain.auth.service;

import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class CheckAccountIdExistsService {

    private final UserFacade userFacade;

    public void execute(String accountId) {
        userFacade.checkUserExists(accountId);
    }
}
