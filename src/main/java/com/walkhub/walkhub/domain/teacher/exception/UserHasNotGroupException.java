package com.walkhub.walkhub.domain.teacher.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class UserHasNotGroupException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new UserHasNotGroupException();

    private UserHasNotGroupException() {
        super(ErrorCode.USER_HAS_NO_GROUP);
    }
}
