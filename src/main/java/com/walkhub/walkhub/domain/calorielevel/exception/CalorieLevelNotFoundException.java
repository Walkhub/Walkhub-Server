package com.walkhub.walkhub.domain.calorielevel.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class CalorieLevelNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new CalorieLevelNotFoundException();

    private CalorieLevelNotFoundException() {
        super(ErrorCode.CALORIE_LEVEL_NOT_FOUND);
    }

}
