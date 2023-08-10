package com.example.edubin.repository;

import com.example.edubin.enitity.UserEntity1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity1,Integer> {
    Optional<UserDetails> findByUsername(String username);
    @Query("select u from UserEntity1 u where u.username = :username")
    Optional<UserEntity1> getIdByUsername(@Param("username") String username);

    boolean existsByEmail(String email);

    Optional<List<UserEntity1>> findByRolesContains(String role1);
    Optional<UserEntity1> findByEmail(String email);
}
