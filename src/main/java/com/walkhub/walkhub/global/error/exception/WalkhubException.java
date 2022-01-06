package com.walkhub.walkhub.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WalkhubException extends RuntimeException {

    private final ErrorCode errorCode;

}
