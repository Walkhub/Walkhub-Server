package com.walkhub.walkhub.domain.su.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class SchoolRootUserExistsException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new SchoolRootUserExistsException();

    private SchoolRootUserExistsException() {
        super(ErrorCode.SCHOOL_ROOT_EXISTS);
    }
}
