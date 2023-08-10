package com.example.edubin.enitity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class CategoryEntity1 extends BaseEntity {

    @NotBlank
    @Column(unique = true)
    private String name;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<CourseEntity1> course;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "categoryEntity")
    private List<BlogEntity1> blogEntity1;

    public CategoryEntity1(String name) {
        this.name=name;
    }
}
