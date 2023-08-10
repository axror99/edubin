package com.example.edubin.dto.response;

import com.example.edubin.enitity.CommentEntity1;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class BlogResponse {

    private int id;
    private String text;
    private LocalDate date;
    private String picture;
    private String personName;
    private String headline;
    private String categoryName;
    private List<CommentEntity1> comments;
}
