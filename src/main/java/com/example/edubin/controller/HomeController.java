package com.example.edubin.controller;

import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.dto.response.MyItems;
import com.example.edubin.enitity.CourseEntity;
import com.example.edubin.service.CourseService;
import com.example.edubin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home/course/")
@RequiredArgsConstructor
public class HomeController {

    private final CourseService courseService;

    private ApiResponse<Void> myCourse(){
        return new ApiResponse<>();
    }

    @GetMapping("/{token}")
    private ApiResponse<MyItems> getStudentsCourse(@PathVariable String token) {
        return new ApiResponse<>("client's course",courseService.getStudentCourses(token));
    }
}
