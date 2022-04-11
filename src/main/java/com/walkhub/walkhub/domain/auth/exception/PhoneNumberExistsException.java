package com.walkhub.walkhub.domain.auth.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class PhoneNumberExistsException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new PhoneNumberExistsException();

    private PhoneNumberExistsException() {
        super(ErrorCode.PHONE_NUMBER_EXISTS);
    }
}
