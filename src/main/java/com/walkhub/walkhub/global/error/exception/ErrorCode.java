package com.walkhub.walkhub.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    SAVE_IMAGE_FALSE(400, "COMMON-404-1", "Save Image False"),

    EXPIRED_JWT(401, "COMMON-401-1", "Expired jwt"),
    INVALID_JWT(401, "COMMON-401-2", "Invalid jwt"),
    UNAUTHORIZED_USER_AUTH_CODE(401, "USER-401-1", "Unauthorized User authCode"),

    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),
    USER_AUTH_CODE_NOT_FOUND(404, "USER-404-2", "User authCode Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "AUTH-404-1", "Refresh Token Not Found"),
    PASSWORD_NOT_FOUND(400, "AUTH-404-2", "Password Not Found"),

    USER_EXISTS(409, "USER-409-1", "User Exists"),
    CREDENTIALS_NOT_FOUND(401, "USER-401-1", "Credentials not found.");

    private final int status;
    private final String code;
    private final String message;

}
