package com.example.edubin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContentRequest {
    private String title;
    private String definition;
    private MultipartFile video;
//    private MultipartFile task;

}
