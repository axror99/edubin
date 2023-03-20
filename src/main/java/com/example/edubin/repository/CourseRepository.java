package com.example.edubin.repository;

import com.example.edubin.enitity.CategoryEntity;
import com.example.edubin.enitity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity,Integer> {
    Optional<CourseEntity> findByName(String name);
    Optional<List<CourseEntity>> findByCategory(CategoryEntity categoryEntity);
}
