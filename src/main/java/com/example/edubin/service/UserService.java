package com.example.edubin.service;

import com.example.edubin.config.utils.GenerateToken;
import com.example.edubin.dto.request.UserLogin;
import com.example.edubin.dto.request.UserRegister;
import com.example.edubin.dto.response.TokenDTO;
import com.example.edubin.enitity.UserEntity;
import com.example.edubin.exception.UserAlreadyExistException;
import com.example.edubin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final GenerateToken generateToken;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    public TokenDTO registerUser(UserRegister userRegister) {
        Optional<UserDetails> userByUsername = userRepository.findByUsername(userRegister.getUsername());
        if (userByUsername.isPresent()) {
            throw new UserAlreadyExistException(MessageFormat.format("username = {0} already exist in database",userRegister.getUsername()));
        }
        UserEntity userEntity = UserEntity.from(userRegister);
        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(userEntity, userEntity.getPassword(), userEntity.getAuthorities());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
        return generateToken.createToken(authentication);
    }

    public TokenDTO login(UserLogin userLogin) {
        UsernamePasswordAuthenticationToken unauthenticated = UsernamePasswordAuthenticationToken.unauthenticated(userLogin.getUsername(), userLogin.getPassword());
        Authentication authentication = daoAuthenticationProvider.authenticate(unauthenticated);
        return generateToken.createToken(authentication);
    }

    public UserEntity findUser(int teacher_id) {
        return userRepository.findById(teacher_id).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("id = {0} user is not in database ", teacher_id)
        ));
    }

    public int findUserByUsername(String username) {
        UserEntity user = userRepository.getIdByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("username {0} not found in database", username)
        ));
        return user.getId();
    }
}
