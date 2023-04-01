package com.example.edubin.enitity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CourseEntity extends BaseEntity{

    private String name;

    @JsonManagedReference
    @ManyToOne
    private CategoryEntity category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<ContentEntity> contents;

    @ManyToOne
    private UserEntity teacher;
    @ManyToMany(mappedBy = "courseList", fetch = FetchType.LAZY)
    private List<StudentEntity> student;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "course")
    private List<CommentEntity> comments;

    private String image;
    private Double price;
    private String definition;
    private String headline;
    private String courseSummery;
    private String requirements;
}
