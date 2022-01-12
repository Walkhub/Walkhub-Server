package com.walkhub.walkhub.global.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class SaveImageFalseException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new SaveImageFalseException();

    private SaveImageFalseException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }
}
