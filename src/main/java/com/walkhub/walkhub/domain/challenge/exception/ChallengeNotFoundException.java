package com.walkhub.walkhub.domain.challenge.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class ChallengeNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new ChallengeNotFoundException();

    private ChallengeNotFoundException() {
        super(ErrorCode.CHALLENGE_NOT_FOUND);
    }

}
