package com.example.edubin.repository;

import com.example.edubin.enitity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserDetails> findByEmail(String email);

    boolean existsByEmail(String email);
}
