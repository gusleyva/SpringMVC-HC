package com.mx.accenture.springmvc.example.service;

import com.mx.accenture.springmvc.example.dao.CourseRepository;
import com.mx.accenture.springmvc.example.dto.CourseDTO;
import com.mx.accenture.springmvc.example.exceptions.ApplicationException;
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
    public CourseDTO findCourseDto(Long idCourse) throws ApplicationException {
        Optional<Course> course = courseRepository.findById(idCourse);
        if (course.isEmpty()) {
            throw COURSE_NOT_FOUND;
        }
        return mapCourseToCourseDTO(course.get());
    }

    @Override
    public CourseDTO updateCourse(Long idCourse, Course course) throws ApplicationException {
        var existingCourse = courseRepository.findById(idCourse);
        if (existingCourse.isEmpty()) {
            throw COURSE_NOT_FOUND;
        }

        validateCourse(course);

        course.setId(idCourse);
        course.setNumberLessons(isNull(course.getNumberLessons())
                ? existingCourse.get().getNumberLessons() : course.getNumberLessons());

        var courseSaved = courseRepository.save(course);
        return modelMapper.map(courseSaved, CourseDTO.class);
    }

    @Override
    public CourseDTO addCourse(Course course) throws ApplicationException {
        validateCourse(course);
        var courseSaved = courseRepository.save(course);
        return modelMapper.map(courseSaved, CourseDTO.class);
    }

    private void validateCourse(Course course) throws ApplicationException {
        StringBuilder courseIssues = new StringBuilder();

        if(isNull(course)) {
            throw new ApplicationException("Course should NOT be null.", HttpStatus.BAD_REQUEST);
        }

        if (isNull(course.getNameCourse()) || course.getNameCourse().isBlank()) {
            courseIssues.append("Course name should NOT be empty.");
        }

        if (isNull(course.getTypeCourse()) || course.getTypeCourse().isBlank()) {
            courseIssues.append("Course type should NOT be empty.");
        }

        if (courseIssues.length() > 0) {
            throw new ApplicationException(courseIssues.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void deleteCourse(Long idCourse) throws ApplicationException {
        var existingCourse = courseRepository.findById(idCourse);
        if (existingCourse.isEmpty()) {
            throw COURSE_NOT_FOUND;
        }
        courseRepository.deleteById(idCourse);
    }

    private CourseDTO mapCourseToCourseDTO(Course course){
        /*CourseDTO filteredCustomer = new CourseDTO(course.getIdCourse(), course.getNameCourse(), course.getTypeCourse(),
                course.getNameTeacher(), course.getNumberStudents(), course.getNumberLessons());*/
        var numberOfStudents = courseRepository.countStudentsByCourseId(course.getId());
        var filteredCustomer = modelMapper.map(course, CourseDTO.class);
        filteredCustomer.setNumberStudents(numberOfStudents);
        return filteredCustomer;
    }

}