package com.library.config;

import com.library.ApiException;
import com.library.ErrorType;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException e){
        log.error("Api Exception occurred. message={}, className={}", e.getErrorMessage(), e.getClass().getName());
        return ResponseEntity.status(e.getHttpStatus())
            .body(new ErrorResponse(e.getErrorMessage(), e.getErrorType()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        log.error("Exception. message={}, className={}", e.getMessage(), e.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse(ErrorType.UNKNOWN_ERROR.getDescription(), ErrorType.UNKNOWN_ERROR));
    }

    // 존재하지 않는경우
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException e){
        log.error("NoResourceFoundException. message={}, className={}", e.getMessage(), e.getClass().getName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(ErrorType.NO_RESOURCE.getDescription(), ErrorType.NO_RESOURCE));
    }

    // 이상한 파라미터 넣는경우 - 인자를 안넣는경우
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
        log.error("MissingServletRequestParameterException. parameterName= {}, "
            + "message={}", e.getParameterName(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(ErrorType.INVALID_PARAMETER.getDescription(), ErrorType.INVALID_PARAMETER));
    }


    //
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        log.error("MethodArgumentTypeMismatchException. "
            + "message={}",  e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(ErrorType.INVALID_PARAMETER.getDescription(), ErrorType.INVALID_PARAMETER));
    }



    // valid
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException e){
        log.error("BindException. message={}, className={}", e.getMessage(), e.getClass().getName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(createMessage(e), ErrorType.INVALID_PARAMETER));
    }


    private String createMessage(BindException e) {
        if(e.getFieldError() != null && e.getFieldError().getDefaultMessage() != null){
            return e.getFieldError().getDefaultMessage();
        }
        return e.getFieldErrors().stream()
            .map(FieldError::getField)
            .collect(Collectors.joining(", ")) + "값들이 정확하지 않습니다";
    }
}
