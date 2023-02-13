package com.mx.accenture.springmvc.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mx.accenture.springmvc.example.dto.CourseDTO;
import com.mx.accenture.springmvc.example.dto.StudentDTO;
import com.mx.accenture.springmvc.example.exceptions.ApplicationException;
import com.mx.accenture.springmvc.example.model.Student;
import com.mx.accenture.springmvc.example.service.StudentService;

import static com.mx.accenture.springmvc.example.exceptions.ExceptionCatalogue.STUDENT_NOT_FOUND;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    private static final String NAME1 = "Test Name";
    private static final String NAME2 = "Test2 Name2";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private StudentService studentService;

    @Test
    void testListAllStudents() throws Exception {
        List<StudentDTO> studentList = new ArrayList<>();
        studentList.add(new StudentDTO(1L, NAME1, Collections.emptyList()));
        studentList.add(new StudentDTO(2L, NAME2, Collections.emptyList()));

        when(studentService.listStudents()).thenReturn(studentList);

        MvcResult result = mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is(NAME1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is(NAME2)))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
        verify(studentService, times(1)).listStudents();
    }

    @Test
    void testFindStudentById() throws Exception, ApplicationException {
        StudentDTO student = new StudentDTO(1L, NAME1, Collections.emptyList());

        when(studentService.findStudent(1L)).thenReturn(student);

        MvcResult result = mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(NAME1)))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
        verify(studentService, times(1)).findStudent(1L);
    }

    @Test
    void testFindStudentByIdNotFound() throws Exception, ApplicationException {

        when(studentService.findStudent(1L)).thenThrow(STUDENT_NOT_FOUND);

        MvcResult result = mockMvc.perform(get("/students/1"))
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(404, result.getResponse().getStatus());
        verify(studentService, times(1)).findStudent(1L);
    }

    @Test
    void testAddStudent() throws Exception, ApplicationException {
        Student student = new Student();
        student.setName(NAME1);
        StudentDTO expected = new StudentDTO(1L, NAME1, Collections.emptyList());

        when(studentService.addStudent(student)).thenReturn(expected);

        var result = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(NAME1)))
                .andExpect(jsonPath("$.id", is(1)))
                .andReturn();

        assertEquals(201, result.getResponse().getStatus());
        verify(studentService, times(1)).addStudent(student);
    }

    @Test
    void deleteStudentTest() throws Exception, ApplicationException {
        // Given
        Long id = 1L;
        doNothing().when(studentService).deleteStudent(id);

        // When
        var result = mockMvc.perform(MockMvcRequestBuilders.delete("/students/{id}", id))
                .andExpect(status().isNoContent())
                .andReturn();

        // Then
        verify(studentService, times(1)).deleteStudent(id);
        assertEquals(204, result.getResponse().getStatus());
    }

    @Test
    void listStudentCoursesTest() throws Exception, ApplicationException {
        Long studentId = 1L;
        List<CourseDTO> expectedCourses = Arrays.asList(
                new CourseDTO(1L, "Math", "Learn Math", "Test1", 2L, 1),
                new CourseDTO(2L, "Science", "Learn Science", "Test2", 3L, 1)
        );
        when(studentService.listStudentCourse(studentId)).thenReturn(expectedCourses);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/students/" + studentId + "/courses"))
                .andExpect(status().isOk())
                .andReturn();

        var actualCourses = mapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<List<CourseDTO>>(){}
        );
        assertEquals(expectedCourses, actualCourses);
        assertEquals(200, result.getResponse().getStatus());
    }
}
