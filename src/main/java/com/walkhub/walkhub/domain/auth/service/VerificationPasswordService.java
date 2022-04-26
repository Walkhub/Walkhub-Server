package com.walkhub.walkhub.domain.auth.service;

import com.walkhub.walkhub.domain.auth.exception.PasswordMismatchException;
import com.walkhub.walkhub.domain.auth.presentation.dto.request.VerificationPasswordRequest;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VerificationPasswordService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    public void execute(VerificationPasswordRequest request) {
        User user = userFacade.getCurrentUser();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

    }
}
