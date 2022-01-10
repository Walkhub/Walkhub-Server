package com.walkhub.walkhub.domain.auth.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class PasswordNotMatchException extends WalkhubException {
    public static final WalkhubException EXCEPTION = new PasswordNotMatchException();
    private PasswordNotMatchException() {
        super(ErrorCode.PASSWORD_NOT_MATCH);
    }
}
