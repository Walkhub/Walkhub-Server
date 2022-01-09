package com.walkhub.walkhub.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),

    EXPIRED_JWT(401,"COMMON-401-1","Expired jwt" ),
    INVALID_JWT(401,"COMMON-401-2", "Invalid jwt");

    private final int status;
    private final String code;
    private final String message;

}
