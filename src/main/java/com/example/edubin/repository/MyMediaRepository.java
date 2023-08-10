package com.example.edubin.repository;

import com.example.edubin.enitity.MyMedia1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyMediaRepository extends JpaRepository<MyMedia1,Integer> {

    Optional<MyMedia1> findByName(String picName);
}
