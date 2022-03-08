package com.walkhub.walkhub.domain.user.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class InvalidCodeException extends WalkhubException {

    public static final WalkhubException EXCEPTION = new InvalidCodeException();

    private InvalidCodeException() {
        super(ErrorCode.INVALID_CODE);
    }

}
