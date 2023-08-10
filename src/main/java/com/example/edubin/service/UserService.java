package com.example.edubin.service;

import com.example.edubin.config.JwtService;
import com.example.edubin.dto.request.UserLogin;
import com.example.edubin.dto.request.UserRegister;
import com.example.edubin.dto.response.PersonInfo;
import com.example.edubin.enitity.UserEntity1;
import com.example.edubin.exception.UserAlreadyExistException;
import com.example.edubin.repository.CourseRepository;
import com.example.edubin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CourseRepository courseRepository;

    public String registerUser(UserRegister userRegister) {
        Optional<UserDetails> userByUsername = userRepository.findByUsername(userRegister.getUsername());
        if (userByUsername.isPresent()) {
            throw new UserAlreadyExistException(MessageFormat.format("username = {0} already exist in database", userRegister.getUsername()));
        }
        UserEntity1 userEntity1 = UserEntity1.from(userRegister);
        userEntity1.setPassword(passwordEncoder.encode(userEntity1.getPassword()));
        userRepository.save(userEntity1);
        return jwtService.generateToken(userEntity1);
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




    public UserEntity1 findUser(int user_id) {
        return userRepository.findById(user_id).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("id = {0} user is not in database ", user_id)
        ));
    }

    public UserEntity1 findUserEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("email = {0} was not found in database", email)
        ));
    }


    public UserEntity1 findUserByUsername(String username) {
        UserEntity1 user = userRepository.getIdByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("username {0} not found in database", username)
        ));
        return user;
    }

    public PersonInfo getMe(String token) {
        PersonInfo personInfo = new PersonInfo();
        String username = jwtService.extractUsername(token.substring(7));
        UserEntity1 user =findUserByUsername(username);
        personInfo.setName(user.getName());
        personInfo.setUsername(user.getUsername());
        personInfo.setEmail(user.getEmail());
        personInfo.setPicture(user.getPicture());
        if (user.getProfession()!=null && !user.getProfession().equals("")){
            personInfo.setProfession(user.getProfession());
        }else {
            personInfo.setProfession("USER");
        }
        personInfo.setRegisteredDate(user.getRegisteredDate());
        personInfo.setSocialMedia(user.getSocialMedia());
        personInfo.setCountCourse(courseRepository.findByTeacherIn(List.of(user)).size());
        personInfo.setCountBook(user.getMerchandiseList().size());
        personInfo.setBirthday(user.getBirthDay());
        personInfo.setId(user.getId());
        return personInfo;
    }
}
