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
    AGENCY_CODE_NOT_MATCH(400, "SCHOOL-400-1", "AgencyCode Not Match"),

    EXPIRED_JWT(401, "COMMON-401-1", "Expired Jwt"),
    INVALID_JWT(401, "COMMON-401-2", "Invalid Jwt"),
    UNAUTHORIZED_USER_AUTH_CODE(401, "USER-401-1", "Unauthorized User AuthCode"),
    INVALID_CLASS_CODE(401, "USER-401-2", "Invalid Class Code"),
    INVALID_SCOPE(401, "CHALLENGE-401-1", "Invalid Scope"),
    PASSWORD_MISMATCH(401, "AUTH-401-1", "Password Mismatch"),
    INVALID_ROLE(401, "GLOBAL-401", "Invalid Role"),

    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),
    USER_AUTH_CODE_NOT_FOUND(404, "USER-404-2", "User AuthCode Not Found"),
    CREDENTIALS_NOT_FOUND(404, "USER-404-3", "Credentials Not Found"),
    SCHOOL_NOT_FOUND(404, "USER-404-4", "School Not Found"),
    GROUP_NOT_FOUND(404, "GROUP-404-1", "Group Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "AUTH-404-1", "Refresh Token Not Found"),
    CHALLENGE_NOT_FOUND(404, "CHALLENGE-404-1", "Challenge Not Found"),
    EXERCISE_NOT_FOUND(404, "EXERCISE-404-1", "Exercise Not Found"),
    NOTICE_NOT_FOUND(404, "NOTICE-404-1", "Notice Not Found"),

    USER_EXISTS(409, "USER-409-1", "User Exists"),
    CREDENTIALS_NOT_FOUND(401, "USER-401-1", "Credentials not found."),

    BADGE_NOT_FOUND(404, "BADGE-404-1", "Badge not found"),
    UNAUTHORIZED_BADGE(401,"BADGE-401-2", "Unauthorized badge"),
  
    ALREADY_CREATED(409, "GROUP-409-1", "Already Created"),
    ALREADY_JOINED(409, "USER-409-2", "Already Joined"),
    ALREADY_PARTICIPATED(409, "CHALLENGE-409-1", "Already Participated");

    private final int status;
    private final String code;
    private final String message;

}
