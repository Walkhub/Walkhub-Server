package com.walkhub.walkhub.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    PASSWORD_NOT_MATCH(400, "AUTH-400-1", "Password Not Match"),
    SAVE_IMAGE_FAILED(400, "COMMON-400-1", "Save Image Failed"),

    EXPIRED_JWT(401, "COMMON-401-1", "Expired jwt"),
    INVALID_JWT(401, "COMMON-401-2", "Invalid jwt"),
    UNAUTHORIZED_USER_AUTH_CODE(401, "USER-401-1", "Unauthorized User authCode"),
    INVALID_CLASS_CODE(401, "USER-401-2", "Invalid class code"),
    INVALID_SCOPE(401, "CHALLENGE-401-1", "Invalid Scope"),
    PASSWORD_MISMATCH(401, "AUTH-401-1", "Password Mismatch"),
    INVALID_ROLE(401, "GLOBAL-401", "Invalid Role"),

    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),
    USER_AUTH_CODE_NOT_FOUND(404, "USER-404-2", "User authCode Not Found"),
    CREDENTIALS_NOT_FOUND(401, "USER-404-3", "Credentials not found."),
    GROUP_NOT_FOUND(404, "GROUP-404-1", "Group not found"),
    REFRESH_TOKEN_NOT_FOUND(404, "AUTH-404-1", "Refresh Token Not Found"),
    CHALLENGE_NOT_FOUND(404, "CHALLENGE-404-1", "Challenge Not Found"),
    EXERCISE_NOT_FOUND(404, "EXERCISE-404-1", "Exercise not found"),

    USER_EXISTS(409, "USER-409-1", "User Exists"),
    ALREADY_JOINED(409, "USER-409-2", "Already joined"),
    ALREADY_PARTICIPATED(409, "CHALLENGE-409-1", "Already Participated");

    private final int status;
    private final String code;
    private final String message;

}
