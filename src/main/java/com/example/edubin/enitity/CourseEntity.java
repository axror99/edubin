package com.example.edubin.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @ManyToOne
    private CategoryEntity category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<ContentEntity> contents;

    @ManyToOne
    private UserEntity teacher;
    private String image;

    private String courseSummery;
    private String requirements;
}
