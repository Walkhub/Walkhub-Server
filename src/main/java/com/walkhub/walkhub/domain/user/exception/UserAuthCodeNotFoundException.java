package com.walkhub.walkhub.domain.user.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class UserAuthCodeNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new UserAuthCodeNotFoundException();

    private UserAuthCodeNotFoundException() {
        super(ErrorCode.USER_AUTH_CODE_NOT_FOUND);
    }
}
