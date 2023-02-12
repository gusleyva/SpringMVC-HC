# Accenture Hiring Challenge
***
## Author - Gustavo Leyva (ovatleyva@gmail.com)
* **[Resume](https://c4cydonia-vercel-blog.vercel.app/resume-qa)**
* **[LinkedIn](https://www.linkedin.com/in/gustavo-leyva-b9493846/)**
* **[Github](https://github.com/gusleyva)**

***

### Reference Documentation

* Get all courses
```aidl
curl --location --request GET 'http://localhost:8080/courses/list' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3NjE2MjAyMiwidXNlcm5hbWUiOiJhZG1pbiJ9.oJ7-Ci-Mz8R3SSvgAVwRCqI4viErVe0rQRsr1Oyhl7g' \
```

* Get courses by Id
 ```aidl
curl --location --request GET 'http://localhost:8080/courses/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3NjE2MjAyMiwidXNlcm5hbWUiOiJhZG1pbiJ9.oJ7-Ci-Mz8R3SSvgAVwRCqI4viErVe0rQRsr1Oyhl7g' \
```
* Create a course
```aidl
curl --location --request POST 'http://localhost:8080/courses/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3NjE2MjAyMiwidXNlcm5hbWUiOiJhZG1pbiJ9.oJ7-Ci-Mz8R3SSvgAVwRCqI4viErVe0rQRsr1Oyhl7g' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=88FCBDA3E4D43B849126C0222A142123' \
--data-raw '{
    "nameCourse": "Test tavo",
    "typeCourse": "Test type",
    "nameTeacher": "Test teacher",
    "numberStudents": 10,
    "numberLessons": 2
}'
```

* Update a course
```aidl
curl --location --request PUT 'http://localhost:8080/courses/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3NjE2MjAyMiwidXNlcm5hbWUiOiJhZG1pbiJ9.oJ7-Ci-Mz8R3SSvgAVwRCqI4viErVe0rQRsr1Oyhl7g' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=88FCBDA3E4D43B849126C0222A142123' \
--data-raw '{
        "idCourse": 1,
        "nameCourse": "Spring MVC 2",
        "typeCourse": "Tech Test",
        "nameTeacher": "Tavo",
        "numberStudents": 10,
        "numberLessons": 1
    }'
```
* Delete a course
```aidl
curl --location --request DELETE 'http://localhost:8080/courses/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3NjE2MjAyMiwidXNlcm5hbWUiOiJhZG1pbiJ9.oJ7-Ci-Mz8R3SSvgAVwRCqI4viErVe0rQRsr1Oyhl7g' \
--header 'Cookie: JSESSIONID=88FCBDA3E4D43B849126C0222A142123'
```

## Built With

* [Spring-boot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [H2](h2database.com/html/main.html) - In-memory database
*
## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details