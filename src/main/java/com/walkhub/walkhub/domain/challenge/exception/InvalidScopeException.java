package com.walkhub.walkhub.domain.challenge.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class InvalidScopeException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new InvalidScopeException();

    private InvalidScopeException() {
        super(ErrorCode.INVALID_SCOPE);
    }

}
