package com.example.edubin.enitity;

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
public class CourseEntity1 extends BaseEntity{

    private String name;

    @JsonManagedReference
    @ManyToOne
    private CategoryEntity1 category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<ContentEntity1> contents;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<UserEntity1> teacher;


    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "course")
    private List<CommentEntity1> comments;

    private String image;
    private Double price;
    @Column(columnDefinition = "TEXT")
    private String definition;
    @Column(columnDefinition = "TEXT")
    private String headline;
    @Column(columnDefinition = "TEXT")
    private String courseSummery;
    @Column(columnDefinition = "TEXT")
    private String requirements;
}
