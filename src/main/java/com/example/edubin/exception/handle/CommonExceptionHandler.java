package com.example.edubin.exception.handle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Controller
@RestControllerAdvice
public class CommonExceptionHandler {


    String recordNotFound(Exception e, Model model){
        model.addAttribute("message",e.getMessage());
        return "404";
    }
}
