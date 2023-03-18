package com.example.edubin.repository;

import com.example.edubin.enitity.MediaContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaContentEntity,Integer> {
}
