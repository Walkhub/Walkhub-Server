package com.walkhub.walkhub.domain.challenge.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class ChallengeNotExpirationException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new ChallengeNotExpirationException();

    private ChallengeNotExpirationException() {
        super(ErrorCode.CHALLENGE_NOT_SUCCESS);
    }
}
