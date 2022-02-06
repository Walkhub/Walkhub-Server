package com.walkhub.walkhub.domain.cheering.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class CanNotCheerMyselfException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new CanNotCheerMyselfException();

    private CanNotCheerMyselfException() {
        super(ErrorCode.CANNOT_CHEER_MYSELF);
    }
}
