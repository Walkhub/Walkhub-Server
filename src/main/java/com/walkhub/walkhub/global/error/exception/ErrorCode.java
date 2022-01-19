package com.walkhub.walkhub.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    PASSWORD_NOT_MATCH(400, "AUTH-400-1", "Password Not Match"),

    EXPIRED_JWT(401, "COMMON-401-1", "Expired jwt"),
    INVALID_JWT(401, "COMMON-401-2", "Invalid jwt"),
    UNAUTHORIZED_USER_AUTH_CODE(401, "USER-401-1", "Unauthorized User authCode"),
    INVALID_SCOPE(401, "CHALLENGE-401-1", "Invalid Scope"),
    CREDENTIALS_NOT_FOUND(401, "USER-401-2", "Credentials not found."),
    PASSWORD_MISMATCH(401, "AUTH-401-1", "Password Mismatch"),

    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),
    USER_AUTH_CODE_NOT_FOUND(404, "USER-404-2", "User authCode Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "AUTH-404-1", "Refresh Token Not Found"),
    PASSWORD_NOT_FOUND(404, "AUTH-404-2", "Password Not Found"),
    CHALLENGE_NOT_FOUND(404, "CHALLENGE-404-1", "Challenge Not Found"),
    SAVE_IMAGE_FAILED(404, "COMMON-404-1", "Save Image False"),

    USER_EXISTS(409, "USER-409-1", "User Exists"),
    ALREADY_PARTICIPATED(409, "CHALLENGE-409-1", "Already Participated");

    private final int status;
    private final String code;
    private final String message;

}
