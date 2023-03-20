package com.example.edubin.enitity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
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
public class CategoryEntity extends BaseEntity {

    @NotBlank
    @Column(unique = true)
    private String name;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<CourseEntity> course;

    public CategoryEntity(String name) {
        this.name=name;
    }
}
