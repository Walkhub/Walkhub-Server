package com.walkhub.walkhub.domain.auth.service;

import com.walkhub.walkhub.domain.auth.exception.PhoneNumberNotFoundException;
import com.walkhub.walkhub.domain.auth.presentation.dto.request.CheckAuthCodeRequest;
import com.walkhub.walkhub.domain.user.domain.UserAuthCode;
import com.walkhub.walkhub.domain.user.domain.repository.UserAuthCodeRepository;
import com.walkhub.walkhub.domain.user.exception.UserAuthCodeNotFoundException;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class CheckAuthCodeExistsService {

    private final UserAuthCodeRepository userAuthCodeRepository;

    public void execute(CheckAuthCodeRequest request) {
        UserAuthCode authCode = userAuthCodeRepository.findById(request.getPhoneNumber())
                .orElseThrow(() -> PhoneNumberNotFoundException.EXCEPTION);

        if (!authCode.getCode().equals(request.getAuthCode())) {
            throw UserAuthCodeNotFoundException.EXCEPTION;
        }
    }

}
