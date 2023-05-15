package com.example.edubin.controller;

import com.example.edubin.dto.response.PersonInfo;
import com.example.edubin.repository.CourseRepository;
import com.example.edubin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/me/")
@RequiredArgsConstructor
public class GetMeController {

    private final UserService userService;

    @GetMapping("/{token}")
    @ResponseStatus(HttpStatus.OK)
    public PersonInfo getMe(@PathVariable String token){
        return userService.getMe(token);
    }

}
