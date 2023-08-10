package com.example.edubin.repository;

import com.example.edubin.enitity.CategoryEntity1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity1,Integer> {
    Optional<CategoryEntity1> findByName(String name);
}
