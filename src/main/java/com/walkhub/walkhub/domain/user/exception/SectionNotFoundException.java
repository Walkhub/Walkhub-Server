package com.walkhub.walkhub.domain.user.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class SectionNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new SectionNotFoundException();

    private SectionNotFoundException() {
        super(ErrorCode.SECTION_NOT_FOUND);
    }

}
