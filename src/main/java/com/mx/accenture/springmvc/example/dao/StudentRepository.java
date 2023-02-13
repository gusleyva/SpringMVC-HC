package com.mx.accenture.springmvc.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mx.accenture.springmvc.example.model.Course;
import com.mx.accenture.springmvc.example.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT sc.course FROM StudentCourse sc WHERE sc.student.id = :studentId")
    List<Course> findCoursesByStudentId(@Param("studentId") Long studentId);
}
