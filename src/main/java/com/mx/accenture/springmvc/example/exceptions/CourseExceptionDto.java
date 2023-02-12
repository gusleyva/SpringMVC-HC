package com.mx.accenture.springmvc.example.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CourseExceptionDto {
    private String message;
    private HttpStatus status;

    public CourseExceptionDto(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus;
    }
}
