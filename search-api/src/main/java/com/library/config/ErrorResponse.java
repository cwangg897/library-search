package com.library.config;

import com.library.ErrorType;
import lombok.Getter;
import org.springframework.http.HttpStatus;


public record ErrorResponse(String errorMessage, ErrorType errorType) {

}
