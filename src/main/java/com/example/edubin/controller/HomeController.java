package com.example.edubin.controller;

import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home/course/")
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    private ApiResponse<Void> myCourse(){
        return new ApiResponse<>();
    }
}
