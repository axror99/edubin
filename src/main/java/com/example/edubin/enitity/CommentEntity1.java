package com.example.edubin.enitity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CommentEntity1 extends BaseEntity{

    private String personName;
    private String date;
    @Column(columnDefinition = "TEXT")
    private String text;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity1 teacher;

    @JsonBackReference
    @ManyToOne
    private CourseEntity1 course;

    @JsonBackReference
    @ManyToOne
    private MerchandiseEntity1 merchandise;

    @JsonBackReference
    @ManyToOne
    private BlogEntity1 blog;
}
