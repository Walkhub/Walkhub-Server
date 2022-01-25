package com.walkhub.walkhub.domain.notice.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class NoticeNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new NoticeNotFoundException();

    private NoticeNotFoundException() {
        super(ErrorCode.NOTICE_NOT_FOUND);
    }
}
