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
    CANNOT_CHEER_MYSELF(400, "SOCKET-400-1", "Can Not Cheer Myself"),
    USER_NOT_EXERCISING(400, "EXERCISE-400-1", "User Not Exercising"),

    EXPIRED_JWT(401, "COMMON-401-1", "Expired Jwt"),
    INVALID_JWT(401, "COMMON-401-2", "Invalid Jwt"),
    UNAUTHORIZED_USER_AUTH_CODE(401, "USER-401-1", "Unauthorized User AuthCode"),
    INVALID_SCOPE(401, "CHALLENGE-401-1", "Invalid Scope"),
    PASSWORD_MISMATCH(401, "AUTH-401-1", "Password Mismatch"),
    INVALID_ROLE(401, "GLOBAL-401-1", "Invalid Role"),
    INVALID_VERIFICATION_CODE(401, "GLOBAL-401-2", "Invalid Verification Code"),

    FORBIDDEN(403, "COMMON-403-1", "Forbidden"),

    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),
    USER_AUTH_CODE_NOT_FOUND(404, "USER-404-2", "User AuthCode Not Found"),
    CREDENTIALS_NOT_FOUND(404, "USER-404-3", "Credentials Not Found"),
    SCHOOL_NOT_FOUND(404, "USER-404-4", "School Not Found"),
    SECTION_NOT_FOUND(404, "SECTION-404-1", "Section Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "AUTH-404-1", "Refresh Token Not Found"),
    PHONE_NUMBER_NOT_FOUND(404, "USER-404-5", "PhoneNumber Not Found"),

    CHALLENGE_NOT_FOUND(404, "CHALLENGE-404-1", "Challenge Not Found"),

    EXERCISE_NOT_FOUND(404, "EXERCISE-404-1", "Exercise Not Found"),
    EXERCISE_ANALYSIS_NOT_FOUND(404, "EXERCISE-404-2", "Exercise Analysis Not Found"),
    NOTICE_NOT_FOUND(404, "NOTICE-404-1", "Notice Not Found"),
    SOCKET_CLIENT_NOT_FOUND(404, "SOCKET-404-1", "Socket Client Not Found"),

    USER_EXISTS(409, "USER-409-1", "User Exists"),

    BADGE_NOT_FOUND(404, "BADGE-404-1", "Badge Not Found"),

    NOTIFICATION_NOT_FOUND(404, "NOTIFICATION-404-1", "Notification Not Found."),

    CALORIE_LEVEL_NOT_FOUND(404, "CALORIE_LEVEL-404-1", "Calorie Level Not Found"),
  
    ALREADY_CREATED(409, "SECTION-409-1", "Already Created"),
    ALREADY_JOINED(409, "USER-409-2", "Already Joined"),
    ALREADY_PARTICIPATED(409, "CHALLENGE-409-1", "Already Participated"),
    ALREADY_EXERCISING(409, "EXERCISE-409-1", "Already Exercising"),

    REDIS_TRANSACTION_EXCEPTION(500, "REDIS-500-1", "Cannot Read Cache From Redis"),
    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal Server Error");

    private final int status;
    private final String code;
    private final String message;

}
