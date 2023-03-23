package com.example.edubin.repository;

import com.example.edubin.enitity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity,Integer> {
    List<EventEntity> findAllByOrderByDate();
}
