package com.walkhub.walkhub.domain.cheering.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class UserNotExercisingException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new UserNotExercisingException();

    private UserNotExercisingException() {
        super(ErrorCode.USER_NOT_EXERCISING);
    }
}
