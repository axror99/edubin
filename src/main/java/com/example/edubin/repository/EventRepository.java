package com.example.edubin.repository;

import com.example.edubin.enitity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity,Integer> {
}
