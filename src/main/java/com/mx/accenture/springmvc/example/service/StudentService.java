package com.mx.accenture.springmvc.example.service;

import java.util.List;

import com.mx.accenture.springmvc.example.dto.CourseDTO;
import com.mx.accenture.springmvc.example.dto.StudentDTO;
import com.mx.accenture.springmvc.example.exceptions.ApplicationException;
import com.mx.accenture.springmvc.example.model.Student;

public interface StudentService {
    List<StudentDTO> listStudents();

    StudentDTO findStudent(Long studentId) throws ApplicationException;

    List<CourseDTO> listStudentCourse(Long studentId) throws ApplicationException;

    StudentDTO addStudent(Student student) throws ApplicationException;

    StudentDTO updateStudent(Long id, Student student) throws ApplicationException;

    void deleteStudent(Long id) throws ApplicationException;
}
