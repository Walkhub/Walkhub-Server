package com.walkhub.walkhub.domain.notification.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class TopicNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new TopicNotFoundException();

    public TopicNotFoundException() {
        super(ErrorCode.TOPIC_NOT_FOUND);
    }
}
