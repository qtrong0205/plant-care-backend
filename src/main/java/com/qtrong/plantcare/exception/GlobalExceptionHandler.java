package com.qtrong.plantcare.exception;

import com.qtrong.plantcare.dto.response.ApiResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ApiResponse handlingRuntimeException(RuntimeException exception){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(1000);
        apiResponse.setMessage(exception.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = AppException.class)
    ApiResponse handlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ApiResponse handlingValidation(RuntimeException exception){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(1000);
        apiResponse.setMessage(exception.getMessage());
        return apiResponse;
    }
}
