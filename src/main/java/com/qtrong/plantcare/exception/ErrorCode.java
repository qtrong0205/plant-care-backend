package com.qtrong.plantcare.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Uncategorized error"),
    USER_EXISTED(HttpStatus.BAD_REQUEST, "User existed"),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "Invalid email format"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "Password must be at least 6 characters or at most 20 characters"),
    USER_NOT_EXISTED(HttpStatus.BAD_REQUEST, "User not exist"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Invalid username or password"),
    PLANT_NOT_EXISTED(HttpStatus.BAD_REQUEST, "Plant not existed"),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "Unauthenticated"),
    HISTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "History not found")
    ;

    private HttpStatusCode code;
    private String message;
}
