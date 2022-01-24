package com.walkhub.walkhub.domain.notification.exception;

import com.walkhub.walkhub.domain.user.exception.UserNotFoundException;
import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class NotificationNotFoundException extends WalkhubException {
    public static final WalkhubException EXCEPTION =
            new NotificationNotFoundException();

    private NotificationNotFoundException() {
        super(ErrorCode.NOTIFICATION_NOT_FOUND);
    }

}
