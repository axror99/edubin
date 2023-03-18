package com.example.edubin.enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CourseEntity extends BaseEntity{

    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private CategoryEntity category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<ContentEntity> contents;

    @ManyToOne
    private UserEntity teacher;
    private String image;

    private String courseSummery;
    private String requirements;
}
