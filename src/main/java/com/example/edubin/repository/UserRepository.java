package com.example.edubin.repository;

import com.example.edubin.enitity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserDetails> findByUsername(String username);
    @Query("select u from UserEntity u where u.username = :username")
    Optional<UserEntity> getIdByUsername(@Param("username") String username);

    boolean existsByEmail(String email);

    Optional<List<UserEntity>> findByRolesContains(String role1);
    Optional<UserEntity> findByEmail(String email);
}
