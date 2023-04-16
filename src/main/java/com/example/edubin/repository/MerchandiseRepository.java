package com.example.edubin.repository;

import com.example.edubin.enitity.MerchandiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MerchandiseRepository extends JpaRepository<MerchandiseEntity,Integer> {
}
