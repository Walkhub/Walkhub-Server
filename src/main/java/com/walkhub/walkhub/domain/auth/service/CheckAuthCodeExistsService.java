package com.walkhub.walkhub.domain.auth.service;

import com.walkhub.walkhub.domain.auth.exception.PhoneNumberNotFoundException;
import com.walkhub.walkhub.domain.auth.presentation.dto.request.CHeckAuthCodeRequest;
import com.walkhub.walkhub.domain.user.domain.UserAuthCode;
import com.walkhub.walkhub.domain.user.domain.repository.UserAuthCodeRepository;
import com.walkhub.walkhub.domain.user.exception.UserAuthCodeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckAuthCodeExistsService {

    private final UserAuthCodeRepository userAuthCodeRepository;

    public void execute(CHeckAuthCodeRequest request) {
        UserAuthCode authCode = userAuthCodeRepository.findById(request.getPhoneNumber())
                .orElseThrow(() -> PhoneNumberNotFoundException.EXCEPTION);

        if (!authCode.getCode().equals(request.getAuthCode())) {
            throw UserAuthCodeNotFoundException.EXCEPTION;
        }
    }

}
