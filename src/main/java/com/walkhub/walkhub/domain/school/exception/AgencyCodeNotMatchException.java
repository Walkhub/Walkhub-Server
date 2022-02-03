package com.walkhub.walkhub.domain.school.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class AgencyCodeNotMatchException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new AgencyCodeNotMatchException();

    private AgencyCodeNotMatchException() {
        super(ErrorCode.AGENCY_CODE_NOT_MATCH);
    }
}
