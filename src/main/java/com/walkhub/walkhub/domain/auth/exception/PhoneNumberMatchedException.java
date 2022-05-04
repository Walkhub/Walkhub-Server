package com.walkhub.walkhub.domain.auth.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class PhoneNumberMatchedException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new PhoneNumberMatchedException();

    private PhoneNumberMatchedException() {
        super(ErrorCode.PHONE_NUMBER_MATCHED);
    }
}
