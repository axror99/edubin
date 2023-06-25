package com.example.edubin.exception.handle;

import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.dto.response.TokenDTO;
import com.example.edubin.exception.PSQLException;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.exception.UserAlreadyExistException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

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
    ApiResponse<?> notUserInContextHolder(Exception e){
        return new ApiResponse<>(e.getMessage(),401);
    }

    @ExceptionHandler({
            AuthenticationException.class,
            CredentialsExpiredException.class,
            LockedException.class,
            DisabledException.class
    })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ApiResponse<?> notAuthenticated(Exception e){
        return new ApiResponse<>(e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    ResponseEntity<?> userAlreadyExist(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(PSQLException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ApiResponse<Void> duplicateItemInPostgersSQL(Exception e){
        return new ApiResponse<>(e.getMessage());
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    ApiResponse<Void> notWriteInFile(Exception e){
        return new ApiResponse<>(e.getMessage());
    }
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ApiResponse<Void> jwt_Expired_time(Exception e){
        return new ApiResponse<>(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    ApiResponse<Void> getNullPointer(Exception e){
        return new ApiResponse<>(e.getMessage());
    }
}
