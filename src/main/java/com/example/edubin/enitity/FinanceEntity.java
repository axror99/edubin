package com.example.edubin.enitity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class FinanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate transactionDate;
    private LocalTime transactionTime;
    private Double price;
    private long card;
    private String expiredDate;
    @OneToOne
    private UserEntity user;
    @OneToOne
    private CourseEntity course;
    @OneToOne
    private MerchandiseEntity merchandise;

}
