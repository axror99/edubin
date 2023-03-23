package com.example.edubin.repository;

import com.example.edubin.enitity.MerchandiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseRepository extends JpaRepository<MerchandiseEntity,Integer> {
}
