package com.mx.accenture.springmvc.example.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApplicationExceptionDto {
    private String message;
    private HttpStatus status;

    public ApplicationExceptionDto(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus;
    }
}
