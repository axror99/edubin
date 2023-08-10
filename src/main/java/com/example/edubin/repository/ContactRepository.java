package com.example.edubin.repository;

import com.example.edubin.enitity.ContactEntity1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity1,Integer> {
}
