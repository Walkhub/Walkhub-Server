package com.walkhub.walkhub.domain.su.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class SchoolRootUserNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new SchoolRootUserNotFoundException();

    private SchoolRootUserNotFoundException() {
        super(ErrorCode.ROOT_USER_NOT_FOUND);
    }
}
