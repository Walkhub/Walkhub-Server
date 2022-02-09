package com.walkhub.walkhub.domain.challenge.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class NotYourChallengeException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new NotYourChallengeException();

    private  NotYourChallengeException() {
        super(ErrorCode.NOT_YOUR_CHALLENGE);
    }
}
