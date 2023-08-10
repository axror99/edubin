package com.example.edubin.enitity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BlogEntity1 extends BaseEntity{

    @Column(columnDefinition="TEXT")
    private String text;
    private LocalDate date;
    private String picture;
    private String personName;
    private String headline;
    @JsonBackReference
    @ManyToOne
    private CategoryEntity1 categoryEntity;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "blog")
    private List<CommentEntity1> comments;
}
