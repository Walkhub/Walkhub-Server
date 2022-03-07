package com.walkhub.walkhub.domain.auth.service;

import com.walkhub.walkhub.domain.auth.presentation.dto.request.CheckAccountIdRequest;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@WalkhubService
public class CheckAccountIdExistsService {

    private final UserFacade userFacade;

    public void execute(CheckAccountIdRequest request) {
        userFacade.checkUserExists(request.getAccountId());
    }
}
