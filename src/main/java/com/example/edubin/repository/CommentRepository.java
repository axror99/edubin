package com.example.edubin.repository;

import com.example.edubin.enitity.CommentEntity1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity1, Integer> {
}
