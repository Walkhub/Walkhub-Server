package com.walkhub.walkhub.domain.challenge.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class NotMySchoolException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new NotMySchoolException();

    private NotMySchoolException() {
        super(ErrorCode.NOT_YOUR_SCHOOL);
    }
}
