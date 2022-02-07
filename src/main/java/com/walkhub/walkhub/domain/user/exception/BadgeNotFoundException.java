package com.walkhub.walkhub.domain.user.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class BadgeNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new BadgeNotFoundException();

    private BadgeNotFoundException() {
        super(ErrorCode.BADGE_NOT_FOUND);
    }
}
