package com.mx.accenture.springmvc.example.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApplicationExceptionDto> taskException(ApplicationException ex) {
        ApplicationExceptionDto taskExceptionDto = new ApplicationExceptionDto(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(taskExceptionDto, new HttpHeaders(), ex.getStatus());
    }
}
