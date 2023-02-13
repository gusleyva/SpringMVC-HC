package com.mx.accenture.springmvc.example.service;

import com.mx.accenture.springmvc.example.dto.CourseDTO;
import com.mx.accenture.springmvc.example.exceptions.ApplicationException;
import com.mx.accenture.springmvc.example.model.Course;

import java.util.List;

public interface ICourseService {

    List<CourseDTO> listCourses();

    CourseDTO updateCourse(Long idCourse, Course course) throws ApplicationException;

    CourseDTO findCourseDto(Long idCourse) throws ApplicationException;

    CourseDTO addCourse(Course course) throws ApplicationException;

    void deleteCourse(Long idCourse) throws ApplicationException;

}