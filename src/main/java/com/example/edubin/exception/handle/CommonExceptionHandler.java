package com.example.edubin.exception.handle;

import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.dto.response.TokenDTO;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.exception.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Controller
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({RecordNotFoundException.class, UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ApiResponse<TokenDTO> recordNotFound(Exception e){
        return new ApiResponse<>(e.getMessage());
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ApiResponse<TokenDTO> notUserInContextHolder(Exception e){
        return new ApiResponse<>(e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    ResponseEntity<?> userAlreadyExist(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }
}
