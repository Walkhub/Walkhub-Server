package com.walkhub.walkhub.global.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class SaveImageFailException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new SaveImageFailException();

    private SaveImageFailException() {
        super(ErrorCode.SAVE_IMAGE_FAIL);
    }
}
