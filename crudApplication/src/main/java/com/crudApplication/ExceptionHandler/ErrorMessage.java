package com.crudApplication.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
public class ErrorMessage {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
}
