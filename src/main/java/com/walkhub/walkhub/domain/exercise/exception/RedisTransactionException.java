package com.walkhub.walkhub.domain.exercise.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class RedisTransactionException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new RedisTransactionException();

    private RedisTransactionException() {
        super(ErrorCode.REDIS_TRANSACTION_EXCEPTION);
    }

}
