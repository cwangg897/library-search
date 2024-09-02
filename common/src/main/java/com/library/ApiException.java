package com.library;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException{
    private final String errorMessage;
    private final ErrorType errorType;
    private final HttpStatus httpStatus;

    public ApiException(String errorMessage, ErrorType errorType, HttpStatus httpStatus){
        this.errorMessage = errorMessage;
        this.errorType = errorType;
        this.httpStatus = httpStatus;
    }
}

// cms에서 방만들기
// ante변경
// 딜러비 변경
// sb, bb 변경이 가능해야함
