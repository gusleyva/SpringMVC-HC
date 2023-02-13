package com.mx.accenture.springmvc.example.exceptions;

import org.springframework.http.HttpStatus;

public class ExceptionCatalogue {
    public static ApplicationException COURSE_NOT_FOUND =
            new ApplicationException("Cannot find course with given id", HttpStatus.NOT_FOUND);

    public static ApplicationException STUDENT_ID_MANDATORY =
            new ApplicationException("Student ID is mandatory", HttpStatus.BAD_REQUEST);

    public static ApplicationException STUDENT_NOT_FOUND =
            new ApplicationException("Cannot find student with given id", HttpStatus.NOT_FOUND);
}
