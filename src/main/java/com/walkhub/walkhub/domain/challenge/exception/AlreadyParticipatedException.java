package com.walkhub.walkhub.domain.challenge.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class AlreadyParticipatedException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new AlreadyParticipatedException();

    private AlreadyParticipatedException() {
        super(ErrorCode.ALREADY_PARTICIPATED);
    }

}
