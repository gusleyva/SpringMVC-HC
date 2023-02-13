INSERT INTO Course (name_course,type_course,name_teacher,number_lessons) VALUES ('Spring MVC','Tech','Kevin',1);
INSERT INTO Course (name_course,type_course,name_teacher,number_lessons) VALUES ('Spring JPA','Tech','Humberto',2);
INSERT INTO Course (name_course,type_course,name_teacher,number_lessons) VALUES ('Spring H2','Tech','Luis',3);
INSERT INTO Course (name_course,type_course,name_teacher,number_lessons) VALUES ('Spring WEB','Tech','Josue',4);
INSERT INTO Course (name_course,type_course,name_teacher,number_lessons) VALUES ('Spring Thymeleaf','Tech','Misael',5);


INSERT INTO Student (name) VALUES ('Tavo');
INSERT INTO Student (name) VALUES ('Juan');
INSERT INTO Student (name) VALUES ('Paco');
INSERT INTO Student (name) VALUES ('Luis');

INSERT INTO student_course (course_id, student_id) VALUES (1, 1);
INSERT INTO student_course (course_id, student_id) VALUES (2, 1);
INSERT INTO student_course (course_id, student_id) VALUES (2, 2);
INSERT INTO student_course (course_id, student_id) VALUES (2, 3);
INSERT INTO student_course (course_id, student_id) VALUES (2, 4);

