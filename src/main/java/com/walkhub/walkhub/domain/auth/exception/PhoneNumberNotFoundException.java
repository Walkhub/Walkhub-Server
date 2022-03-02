package com.walkhub.walkhub.domain.auth.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class PhoneNumberNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new PhoneNumberNotFoundException();

    private PhoneNumberNotFoundException() {
        super(ErrorCode.PHONE_NUMBER_NOT_FOUND);
    }
}
