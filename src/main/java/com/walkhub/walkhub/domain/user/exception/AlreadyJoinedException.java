package com.walkhub.walkhub.domain.user.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class AlreadyJoinedException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new AlreadyJoinedException();

    private AlreadyJoinedException() {
        super(ErrorCode.ALREADY_JOINED);
    }

}
