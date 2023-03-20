package com.example.edubin.repository;

import com.example.edubin.enitity.MediaContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<MediaContentEntity,Integer> {
    Optional<MediaContentEntity> findByContent_Id(int id);
}
