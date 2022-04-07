package com.walkhub.walkhub.domain.auth.service;

import com.walkhub.walkhub.domain.auth.presentation.dto.request.CheckAuthCodeRequest;
import com.walkhub.walkhub.domain.user.domain.UserAuthCode;
import com.walkhub.walkhub.domain.user.domain.repository.UserAuthCodeRepository;
import com.walkhub.walkhub.domain.user.exception.InvalidCodeException;
import com.walkhub.walkhub.domain.user.exception.UserAuthCodeNotFoundException;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class CheckAuthCodeExistsService {

    private final UserAuthCodeRepository userAuthCodeRepository;
    private final PasswordEncoder passwordEncoder;

    public void execute(CheckAuthCodeRequest request) {
        UserAuthCode authCode = userAuthCodeRepository.findById(request.getPhoneNumber())
                .orElseThrow(() -> UserAuthCodeNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getAuthCode(), authCode.getCode())) {
            throw InvalidCodeException.EXCEPTION;
        }
    }

}
