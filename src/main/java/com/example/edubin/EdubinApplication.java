package com.example.edubin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EdubinApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdubinApplication.class, args);
    }


//    file:///D:/EduBin/edubin/src/main/resources/templates/teachers-singel.html?teacherId=2
//    file:///D:/EduBin/edubin/src/main/resources/templates/teacher-singel.html?teacherID=14
}
