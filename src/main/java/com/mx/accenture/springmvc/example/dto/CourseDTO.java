package com.mx.accenture.springmvc.example.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO implements Serializable {
    private Long idCourse;
    private String nameCourse;
    private String typeCourse;
    private String nameTeacher;
    private Long numberStudents;
    private int numberLessons;
}


