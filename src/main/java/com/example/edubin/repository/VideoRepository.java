package com.example.edubin.repository;

import com.example.edubin.enitity.VideoEntity1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity1, Integer> {

    VideoEntity1 findByTitle(String title);
}
