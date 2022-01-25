package com.walkhub.walkhub.domain.teacher.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class AlreadyCreatedException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new AlreadyCreatedException();

    private AlreadyCreatedException() {
        super(ErrorCode.ALREADY_CREATED);
    }

}
