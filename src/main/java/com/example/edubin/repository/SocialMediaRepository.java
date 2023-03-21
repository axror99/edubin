package com.example.edubin.repository;

import com.example.edubin.enitity.SocialMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialMediaRepository extends JpaRepository<SocialMediaEntity,Integer> {
    Optional<SocialMediaEntity> findByUserId(int id);
}
