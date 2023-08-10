package com.example.edubin.repository;

import com.example.edubin.enitity.CategoryEntity1;
import com.example.edubin.enitity.CourseEntity1;
import com.example.edubin.enitity.UserEntity1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity1,Integer> {

    Optional<List<CourseEntity1>> findByCategory(CategoryEntity1 categoryEntity1);
    List<CourseEntity1> findByTeacherIn(List<UserEntity1> teacher);

}
