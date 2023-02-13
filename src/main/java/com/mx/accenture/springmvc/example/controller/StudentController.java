package com.mx.accenture.springmvc.example.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mx.accenture.springmvc.example.dao.CourseRepository;
import com.mx.accenture.springmvc.example.dao.StudentRepository;
import com.mx.accenture.springmvc.example.dto.CourseDTO;
import com.mx.accenture.springmvc.example.dto.StudentDTO;
import com.mx.accenture.springmvc.example.exceptions.ApplicationException;
import com.mx.accenture.springmvc.example.model.Course;
import com.mx.accenture.springmvc.example.model.Student;
import com.mx.accenture.springmvc.example.service.StudentService;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/students")
@Data
@AllArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    // private final CourseRepository courseRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<StudentDTO> listAllStudents() {
        return studentService.listStudents();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public StudentDTO findStudentById(@PathVariable Long id) throws ApplicationException {
        return studentService.findStudent(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public StudentDTO addStudent(@RequestBody Student student) throws ApplicationException {
        return studentService.addStudent(student);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody Student student)
            throws ApplicationException {
        return studentService.updateStudent(id, student);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) throws ApplicationException {
        studentService.deleteStudent(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/courses")
    public List<CourseDTO> listStudentCourses(@PathVariable Long id) throws ApplicationException {
        return studentService.listStudentCourse(id);
    }
}
