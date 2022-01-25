package com.walkhub.walkhub.domain.user.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class InvalidClassCodeException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new InvalidClassCodeException();

    private InvalidClassCodeException() {
        super(ErrorCode.INVALID_CLASS_CODE);
    }

}
