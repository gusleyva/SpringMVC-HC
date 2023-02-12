package com.mx.accenture.springmvc.example.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CourseException extends Throwable {
    private String message;
    private HttpStatus status;

    public CourseException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus;
    }
}
