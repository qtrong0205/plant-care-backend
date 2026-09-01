package com.qtrong.plantcare.exception;

import com.qtrong.plantcare.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse<?>> handlingRuntimeException(RuntimeException exception) {
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage(exception.getMessage());
        return ResponseEntity
                .status(apiResponse.getCode())
                .body(apiResponse);
    }

    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse<?>> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<?>> handlingValidation(RuntimeException exception) {
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage(exception.getMessage());
        return ResponseEntity
                .status(apiResponse.getCode())
                .body(apiResponse);
    }
}