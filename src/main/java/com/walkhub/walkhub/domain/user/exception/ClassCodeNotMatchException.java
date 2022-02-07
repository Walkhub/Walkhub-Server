package com.walkhub.walkhub.domain.user.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class ClassCodeNotMatchException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new ClassCodeNotMatchException();

    private ClassCodeNotMatchException() {
        super(ErrorCode.CLASS_CODE_NOT_MATCH);
    }

}
