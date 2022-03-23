package com.walkhub.walkhub.global.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class CredentialsNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new CredentialsNotFoundException();

    private CredentialsNotFoundException() {
        super(ErrorCode.CREDENTIALS_NOT_FOUND);
    }

}