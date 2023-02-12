package com.mx.accenture.springmvc.example.exceptions;

import org.springframework.http.HttpStatus;

public class ExceptionCatalogue {
    public static CourseException COURSE_NOT_FOUND =
            new CourseException("Cannot find course with given id", HttpStatus.NOT_FOUND);
}
