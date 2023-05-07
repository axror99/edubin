package com.example.edubin.controller;

import com.example.edubin.dto.response.PersonInfo;
import com.example.edubin.repository.CourseRepository;
import com.example.edubin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home/me/")
@RequiredArgsConstructor
public class GetMeController {

    private final UserService userService;

    @GetMapping("/{token}")
    public PersonInfo getMe(@PathVariable String token){
        return userService.getMe(token);
    }

}
