package com.example.edubin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateHimself extends Employee{

    private LocalDate birthDay;
    private String telegram;
    private String instagram;
    private String google;
    private String linkedIn;
    private String facebook;
    private String twitter;
}
