package com.mx.accenture.springmvc.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mx.accenture.springmvc.example.dto.CourseDTO;
import com.mx.accenture.springmvc.example.exceptions.ApplicationException;
import com.mx.accenture.springmvc.example.model.Course;
import com.mx.accenture.springmvc.example.service.ICourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    public List<CourseDTO> listCourses(){
        List<CourseDTO> listRepository = courseService.listCourses();
        return listRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CourseDTO findCourse(@PathVariable Long id) throws ApplicationException {
        return courseService.findCourseDto(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CourseDTO createCourse(@RequestBody Course course) throws ApplicationException {
        return courseService.addCourse(course);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CourseDTO updateCourse(@PathVariable Long id, @RequestBody Course course) throws ApplicationException {
        return courseService.updateCourse(id, course);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) throws ApplicationException {
        courseService.deleteCourse(id);
    }

}
