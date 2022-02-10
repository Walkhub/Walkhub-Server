package com.walkhub.walkhub.domain.exercise.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class ExerciseAnalysisNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new ExerciseAnalysisNotFoundException();

    private ExerciseAnalysisNotFoundException() {
        super(ErrorCode.EXERCISE_ANALYSIS_NOT_FOUND);
    }

}
