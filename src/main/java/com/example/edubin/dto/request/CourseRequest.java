package com.example.edubin.dto.request;

import com.example.edubin.enitity.ContentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {

    private String name;
    private int category_di;
//    private List<ContentEntity> contentEntities;
    private int teacher_id;
    private MultipartFile image;
    private Double price;
    private String courseSummery;
    private String requirements;
    private String headline;
    private String definition;
}
