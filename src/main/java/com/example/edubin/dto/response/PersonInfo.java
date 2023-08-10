package com.example.edubin.dto.response;

import com.example.edubin.enitity.SocialMediaEntity1;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonInfo {

    private int id;
    private String username;
    private String picture;
    private String name;
    private String email;
    private String profession;
    private LocalDate birthday;
    private LocalDate registeredDate;
    private SocialMediaEntity1 socialMedia;
    private int countCourse;
    private int countBook;
}
