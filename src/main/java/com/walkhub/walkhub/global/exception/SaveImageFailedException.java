package com.walkhub.walkhub.global.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class SaveImageFaildException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new SaveImageFaildException();

    private SaveImageFaildException() {
        super(ErrorCode.SAVE_IMAGE_FAIL);
    }
}
