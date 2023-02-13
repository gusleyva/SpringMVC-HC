package com.mx.accenture.springmvc.example.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mx.accenture.springmvc.example.dao.StudentRepository;
import com.mx.accenture.springmvc.example.dto.CourseDTO;
import com.mx.accenture.springmvc.example.dto.StudentDTO;
import com.mx.accenture.springmvc.example.exceptions.ApplicationException;
import com.mx.accenture.springmvc.example.model.Course;
import com.mx.accenture.springmvc.example.model.Student;

import lombok.AllArgsConstructor;

import static com.mx.accenture.springmvc.example.exceptions.ExceptionCatalogue.STUDENT_ID_MANDATORY;
import static com.mx.accenture.springmvc.example.exceptions.ExceptionCatalogue.STUDENT_NOT_FOUND;
import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class StudentServiceImp implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDTO> listStudents() {
        var students = studentRepository.findAll();
        List<StudentDTO> studentDTOS =
                modelMapper.map(students, new TypeToken<List<StudentDTO>>() {}.getType());
        return studentDTOS;
    }

    @Override
    public StudentDTO findStudent(Long studentId) throws ApplicationException {
        return findStudentById(studentId);
    }

    private StudentDTO findStudentById(Long studentId) throws ApplicationException {
        if (isNull(studentId)) {
            throw STUDENT_ID_MANDATORY;
        }

        var student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            throw STUDENT_NOT_FOUND;
        }
        return modelMapper.map(student.get(), StudentDTO.class);
    }

    @Override
    public List<CourseDTO> listStudentCourse(Long studentId) throws ApplicationException {
        var student = findStudentById(studentId);
        var courses = studentRepository.findCoursesByStudentId(student.getId());
        List<CourseDTO> coursesDto =
                modelMapper.map(courses, new TypeToken<List<CourseDTO>>() {}.getType());
        return coursesDto;
    }

    private StudentDTO mapStudentToStudentWithCourses(Student student, List<CourseDTO> courses) {
        var studentDto = modelMapper.map(student, StudentDTO.class);
        studentDto.setCourses(courses);
        return  studentDto;
    }

    @Override
    public StudentDTO addStudent(Student student) throws ApplicationException {
        validateStudent(student);
        var studentSaved = studentRepository.save(student);
        return modelMapper.map(studentSaved, StudentDTO.class);
    }

    private void validateStudent(Student student) throws ApplicationException {
        StringBuilder studentIssues = new StringBuilder();

        if(isNull(student)) {
            throw new ApplicationException("Student should NOT be null.", HttpStatus.BAD_REQUEST);
        }

        if (isNull(student.getName()) || student.getName().isBlank()) {
            studentIssues.append("Student name should NOT be empty.");
        }

        if (studentIssues.length() > 0) {
            throw new ApplicationException(studentIssues.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public StudentDTO updateStudent(Long id, Student student) throws ApplicationException {
        var existingStudent = studentRepository.findById(id);
        if (existingStudent.isEmpty()) {
            throw STUDENT_NOT_FOUND;
        }

        validateStudent(student);

        student.setId(id);
        var studentSaved = studentRepository.save(student);
        return modelMapper.map(studentSaved, StudentDTO.class);
    }

    @Override
    public void deleteStudent(Long id) throws ApplicationException {
        var existingStudent = studentRepository.findCoursesByStudentId(id);
        if (existingStudent.isEmpty()) {
            throw STUDENT_NOT_FOUND;
        }
        studentRepository.deleteById(id);
    }
}
