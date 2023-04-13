package com.example.edubin.service;

import com.example.edubin.config.JwtService;
import com.example.edubin.dto.request.UserLogin;
import com.example.edubin.dto.request.UserRegister;
import com.example.edubin.enitity.UserEntity;
import com.example.edubin.exception.UserAlreadyExistException;
import com.example.edubin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public String registerUser(UserRegister userRegister) {
        Optional<UserDetails> userByUsername = userRepository.findByUsername(userRegister.getUsername());
        if (userByUsername.isPresent()) {
            throw new UserAlreadyExistException(MessageFormat.format("username = {0} already exist in database",userRegister.getUsername()));
        }
        UserEntity userEntity = UserEntity.from(userRegister);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
        return  jwtService.generateToken(userEntity);
    }

    public String login(UserLogin userLogin) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getUsername(),
                        userLogin.getPassword()
                )
        );

        var user = userRepository.findByUsername(userLogin.getUsername()).orElseThrow();
        return jwtService.generateToken(user);
    }



    public UserEntity findUser(int user_id) {
        return userRepository.findById(user_id).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("id = {0} user is not in database ", user_id)
        ));
    }

    public UserEntity findUserByUsername(String username) {
        UserEntity user = userRepository.getIdByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("username {0} not found in database", username)
        ));
        return user;
    }
}
