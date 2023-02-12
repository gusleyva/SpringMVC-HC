package com.mx.accenture.springmvc.example.service;

import com.mx.accenture.springmvc.example.dao.CourseRepository;
import com.mx.accenture.springmvc.example.dto.CourseDTO;
import com.mx.accenture.springmvc.example.exceptions.CourseException;
import com.mx.accenture.springmvc.example.model.Course;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import static com.mx.accenture.springmvc.example.exceptions.ExceptionCatalogue.COURSE_NOT_FOUND;
import static java.util.Objects.isNull;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<CourseDTO> listCourses() {
        var courses = courseRepository.findAll();
        var coursesDto = courses.stream()
                .map(this::mapCourseToCourseDTO)
                .collect(Collectors.toList());
        return coursesDto;
    }

    @Override
    public CourseDTO findCourseDto(int idCourse) throws CourseException {
        Optional<Course> course = courseRepository.findById(idCourse);
        if (course.isEmpty()) {
            throw COURSE_NOT_FOUND;
        }
        return modelMapper.map(course.get(), CourseDTO.class);
    }

    @Override
    public CourseDTO updateCourse(int idCourse, Course course) throws CourseException {
        validateCourse(course);
        var existingCourse = courseRepository.findById(idCourse);
        if (existingCourse.isEmpty()) {
            throw COURSE_NOT_FOUND;
        }

        var courseSaved = courseRepository.save(course);
        return modelMapper.map(courseSaved, CourseDTO.class);
    }

    @Override
    public CourseDTO addCourse(Course course) throws CourseException {
        validateCourse(course);
        var courseSaved = courseRepository.save(course);
        return modelMapper.map(courseSaved, CourseDTO.class);
    }

    private void validateCourse(Course course) throws CourseException {
        StringBuilder courseIssues = new StringBuilder();

        if(isNull(course)) {
            throw new CourseException("Course should NOT be null.", HttpStatus.BAD_REQUEST);
        }

        if (isNull(course.getNameCourse()) || course.getNameCourse().isBlank()) {
            courseIssues.append("Course name should NOT be empty.");
        }

        if (isNull(course.getTypeCourse()) || course.getTypeCourse().isBlank()) {
            courseIssues.append("Course type should NOT be empty.");
        }

        if (courseIssues.length() > 0) {
            throw new CourseException(courseIssues.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void deleteCourse(int idCourse) {
        courseRepository.deleteById(idCourse);
    }

    private CourseDTO mapCourseToCourseDTO(Course course){
        /*CourseDTO filteredCustomer = new CourseDTO(course.getIdCourse(), course.getNameCourse(), course.getTypeCourse(),
                course.getNameTeacher(), course.getNumberStudents(), course.getNumberLessons());*/
        var filteredCustomer = modelMapper.map(course, CourseDTO.class);
        return filteredCustomer;
    }

}