package com.qtrong.plantcare.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(409, "User existed");


    private int code;
    private String message;
}
