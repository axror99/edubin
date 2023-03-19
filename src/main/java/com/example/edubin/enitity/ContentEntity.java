package com.example.edubin.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ContentEntity extends BaseEntity{

    private String title;
    @Column(columnDefinition="TEXT")
    private String definition;
    private String videoName;
    private String taskName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private CourseEntity course;
}
