package com.example.edubin.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
public abstract class Employee {
    private String name;
    private String username;
    private String password;
    private String profession;
    private String email;
    private MultipartFile picture;
    private String about;
    private String achievement;
    private String myObjective;
}
