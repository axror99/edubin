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
public class BlogEntity extends BaseEntity{

    @Column(columnDefinition="TEXT")
    private String text;
    private LocalDate date;
    private String picture;
    private String personName;
    private String headline;
    @JsonBackReference
    @ManyToOne
    private CategoryEntity categoryEntity;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "blog")
    private List<CommentEntity> comments;
}
