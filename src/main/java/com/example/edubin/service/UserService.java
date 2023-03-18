package com.example.edubin.service;

import com.example.edubin.config.utils.GenerateToken;
import com.example.edubin.dto.request.UserLogin;
import com.example.edubin.dto.request.UserRegister;
import com.example.edubin.dto.response.TokenDTO;
import com.example.edubin.enitity.UserEntity;
import com.example.edubin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final GenerateToken generateToken;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    public TokenDTO registerUser(UserRegister userRegister) {
        UserEntity userEntity = UserEntity.from(userRegister);
        Authentication authentication=UsernamePasswordAuthenticationToken.authenticated(userEntity, userEntity.getPassword(), userEntity.getAuthorities());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
        return generateToken.createToken(authentication);
    }

    public TokenDTO login(UserLogin userLogin) {
        Authentication authentication = daoAuthenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(userLogin.getUsername(), userLogin.getPassword()));
        return generateToken.createToken(authentication);
    }
}
