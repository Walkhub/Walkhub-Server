package com.walkhub.walkhub.global.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class VerificationCodeNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new VerificationCodeNotFoundException();

    private VerificationCodeNotFoundException() {
        super(ErrorCode.VERIFICATION_CODE_NOT_FOUND);
    }
}
