package com.example.edubin.repository;

import com.example.edubin.enitity.EventEntity1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity1,Integer> {
    List<EventEntity1> findAllByOrderByDate();
}
