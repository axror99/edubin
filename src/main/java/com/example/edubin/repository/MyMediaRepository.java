package com.example.edubin.repository;

import com.example.edubin.enitity.MyMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyMediaRepository extends JpaRepository<MyMedia,Integer> {

    Optional<MyMedia> findByName(String picName);
}
