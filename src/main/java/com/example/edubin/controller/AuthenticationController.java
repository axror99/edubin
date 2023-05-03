package com.example.edubin.controller;


import com.example.edubin.dto.request.UserLogin;
import com.example.edubin.dto.request.UserRegister;
import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.dto.response.TokenDTO;
import com.example.edubin.enitity.UserEntity;
import com.example.edubin.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ApiResponse<String> register(@Valid @RequestBody UserRegister userRegister)
    {
        String  token=userService.registerUser(userRegister);
        return new ApiResponse<>("registered successfully", token);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<String> login(@RequestBody UserLogin userLogin) {
        String  token = userService.login(userLogin);
        return new ApiResponse<>("login was passed successfully",token);
    }

    @PostMapping("/login/super")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private ApiResponse<String> loginForSuperAdmin(@RequestBody UserLogin userLogin) {
        String  token = userService.loginSuperAdmin(userLogin);
       return new ApiResponse<>("login was passed successfully", Objects.requireNonNullElse(token, "****"));
    }
}