package com.example.edubin.enitity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CommentEntity extends BaseEntity{

    private String personName;
    private LocalDate date;
    @Column(columnDefinition = "TEXT")
    private String text;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity teacher;

    @JsonBackReference
    @ManyToOne
    private CourseEntity course;

    @JsonBackReference
    @ManyToOne
    private MerchandiseEntity merchandise;

    @JsonBackReference
    @ManyToOne
    private BlogEntity blog;
}
