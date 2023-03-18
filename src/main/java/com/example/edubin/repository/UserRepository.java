package com.example.edubin.repository;

import com.example.edubin.enitity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserDetails> findByUsername(String username);

    boolean existsByEmail(String email);

    Optional<List<UserEntity>> findByRolesContains(String role1);
}
