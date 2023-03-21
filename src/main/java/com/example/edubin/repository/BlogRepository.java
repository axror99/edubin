package com.example.edubin.repository;

import com.example.edubin.enitity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<BlogEntity,Integer> {
    Optional<List<BlogEntity>> findByCategoryEntity_Id(int id);
}
