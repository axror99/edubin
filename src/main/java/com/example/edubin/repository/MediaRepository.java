package com.example.edubin.repository;

import com.example.edubin.enitity.MediaContentEntity1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<MediaContentEntity1,Integer> {
    Optional<MediaContentEntity1> findByContent_Id(int id);
}
