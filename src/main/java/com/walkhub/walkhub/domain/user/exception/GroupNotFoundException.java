package com.walkhub.walkhub.domain.user.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class GroupNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new GroupNotFoundException();

    private GroupNotFoundException() {
        super(ErrorCode.GROUP_NOT_FOUND);
    }

}
