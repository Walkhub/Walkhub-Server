package com.walkhub.walkhub.domain.badge.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class BadgeTypeNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new BadgeTypeNotFoundException();

    private BadgeTypeNotFoundException() {
        super(ErrorCode.BADGE_TYPE_NOT_FOUND);
    }
}
