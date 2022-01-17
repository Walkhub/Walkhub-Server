package com.walkhub.walkhub.domain.auth.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class PasswordNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION = new PasswordNotFoundException();

    private PasswordNotFoundException() {
        super(ErrorCode.PASSWORD_NOT_FOUND);
    }
}
