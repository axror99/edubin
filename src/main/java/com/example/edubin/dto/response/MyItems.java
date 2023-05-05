package com.example.edubin.dto.response;

import com.example.edubin.enitity.CourseEntity;
import com.example.edubin.enitity.MerchandiseEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MyItems {
    private List<CourseEntity> courses;
    private String name;
    private String username;
    private String email;
    private LocalDate birthday;
    private List<MerchandiseEntity> merchandises;
    private String picture;
    private List<String> roles;
}
