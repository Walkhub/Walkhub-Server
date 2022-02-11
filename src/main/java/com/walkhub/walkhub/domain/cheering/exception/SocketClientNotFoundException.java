package com.walkhub.walkhub.domain.cheering.exception;

import com.walkhub.walkhub.global.error.exception.ErrorCode;
import com.walkhub.walkhub.global.error.exception.WalkhubException;

public class SocketClientNotFoundException extends WalkhubException {

    public static final WalkhubException EXCEPTION =
            new SocketClientNotFoundException();

    private SocketClientNotFoundException() {
        super(ErrorCode.SOCKET_CLIENT_NOT_FOUND);
    }
}
