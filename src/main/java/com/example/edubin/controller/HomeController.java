package com.example.edubin.controller;

import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.dto.response.MyItems;
import com.example.edubin.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home/course/")
@RequiredArgsConstructor
public class HomeController {

    private final CourseService courseService;

    private ApiResponse<Void> myCourse(){
        return new ApiResponse<>();
    }

    @GetMapping("/{token}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<MyItems> getStudentsCourse(@PathVariable String token) {
        return new ApiResponse<>("client's course",courseService.getStudentCourses(token));
    }
}
