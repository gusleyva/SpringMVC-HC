package com.mx.accenture.springmvc.example.dao;

import com.mx.accenture.springmvc.example.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository <Course, Long> {
    List<Course> findAll();

    @Query("SELECT COUNT(sc) FROM StudentCourse sc WHERE sc.course.id = :courseId")
    long countStudentsByCourseId(@Param("courseId") Long courseId);
}
