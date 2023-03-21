package com.example.edubin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequest {

    private String text;
    private LocalDate date;
    private MultipartFile picture;
    private String personName;
    private String headline;
}
