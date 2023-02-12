package com.mx.accenture.springmvc.example.service;

import com.mx.accenture.springmvc.example.dto.CourseDTO;
import com.mx.accenture.springmvc.example.exceptions.CourseException;
import com.mx.accenture.springmvc.example.model.Course;

import java.util.List;

public interface ICourseService {

    List<CourseDTO> listCourses();

    CourseDTO updateCourse(int idCourse, Course course) throws CourseException;

    CourseDTO findCourseDto(int idCourse) throws CourseException;

    CourseDTO addCourse(Course course) throws CourseException;

    void deleteCourse(int idCourse);

}