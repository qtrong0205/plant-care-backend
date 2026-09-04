package com.qtrong.plantcare.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_ERROR(500, "Uncategorized error"),
    USER_EXISTED(409, "User existed"),
    INVALID_EMAIL(400, "Invalid email format"),
    INVALID_PASSWORD(400, "Password must be at least 6 characters or at most 20 characters"),
    USER_NOT_EXISTED(400, "User not exist"),
    INVALID_CREDENTIALS(401, "Invalid username or password"),
    PLANT_NOT_EXISTED(400, "Plant not existed")
    ;

    private int code;
    private String message;
}
