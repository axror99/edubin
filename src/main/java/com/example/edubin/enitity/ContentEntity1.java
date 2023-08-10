package com.example.edubin.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ContentEntity1 extends BaseEntity{

    private String title;
    @Column(columnDefinition="TEXT")
    private String definition;
    private String videoName;
    private String taskName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private CourseEntity1 course;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE,mappedBy ="content")
    private MediaContentEntity1 mediaContent;
}
