package com.example.edubin.enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ContentEntity extends BaseEntity{

    @ManyToOne(cascade = CascadeType.REMOVE)
    private CourseEntity courseEntity;

    private String contentName;
}
