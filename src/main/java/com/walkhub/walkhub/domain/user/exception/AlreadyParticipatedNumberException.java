package com.walkhub.walkhub.domain.user.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class AlreadyParticipatedNumberException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new AlreadyParticipatedNumberException();

    private AlreadyParticipatedNumberException() {
        super(ErrorCode.ALREADY_PARTICIPATED_NUMBER);
    }
}
