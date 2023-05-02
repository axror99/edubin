package com.example.edubin.repository;

import com.example.edubin.enitity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity,Integer> {
}
