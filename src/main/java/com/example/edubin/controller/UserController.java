package com.example.edubin.controller;

import com.example.edubin.config.utils.GenerateToken;
import com.example.edubin.dto.request.UserRegister;
import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.dto.response.TokenDTO;
import com.example.edubin.enitity.UserEntity;
import com.example.edubin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public final class UserController {

    private final UserService userService;
    private final GenerateToken generateToken;

    @PostMapping("/register")
    private ApiResponse<TokenDTO> addUser(@RequestBody UserRegister userRegister){
        UserEntity userEntity = UserEntity.from(userRegister);
        Authentication authentication=new UsernamePasswordAuthenticationToken(userEntity,userEntity.getPassword(),userEntity.getAuthorities());
        TokenDTO token = generateToken.createToken(authentication);
        System.out.println(token);
        return new ApiResponse<>("qale",token);
    }
}
