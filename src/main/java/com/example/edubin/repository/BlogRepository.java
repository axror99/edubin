package com.example.edubin.repository;

import com.example.edubin.enitity.BlogEntity1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<BlogEntity1,Integer> {
    Optional<List<BlogEntity1>> findByCategoryEntity_Id(int id);

    Page<BlogEntity1> findByCategoryEntity_Id(int id, Pageable page);
}
