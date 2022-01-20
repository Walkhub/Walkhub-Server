package com.walkhub.walkhub.domain.exercise.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class ExerciseNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new ExerciseNotFoundException();

    private ExerciseNotFoundException() {
        super(ErrorCode.EXERCISE_NOT_FOUND);
    }

}
