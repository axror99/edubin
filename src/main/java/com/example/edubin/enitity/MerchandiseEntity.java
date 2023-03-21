package com.example.edubin.enitity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
public class MerchandiseEntity extends BaseEntity {

    private String headline;
    private Double price;
    private String picture;
    @Column(columnDefinition="TEXT")
    private String definition;
    private int countOfLike;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "merchandise")
    private List<CommentEntity> comments;
}
