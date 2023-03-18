package com.example.edubin.enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ContentEntity extends BaseEntity{

    private String title;
    @Column(columnDefinition="TEXT")
    private String definition;
    private String videoName;
    private String taskName;
    @ManyToOne
    private CourseEntity course;
}
