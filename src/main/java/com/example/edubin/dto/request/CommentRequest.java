package com.example.edubin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private String firstName;
    private String lastName;
    private LocalDate date;
    private String text;
}
