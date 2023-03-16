package com.example.edubin.controller;

import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public final class CourseController {

    private final CourseService courseService;

    private ApiResponse addCourse(){
        return null;
    }
}
