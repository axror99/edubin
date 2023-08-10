package com.example.edubin.dto.response;

import com.example.edubin.enitity.CourseEntity1;
import com.example.edubin.enitity.MerchandiseEntity1;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MyItems {
    private List<CourseEntity1> courses;
    private String name;
    private String username;
    private String email;
    private LocalDate birthday;
    private List<MerchandiseEntity1> merchandises;
    private String picture;
    private List<String> roles;
}
