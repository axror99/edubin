package com.example.edubin.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class EventEntity extends BaseEntity{

    private String picture;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime finishTime;
    @Column(columnDefinition = "TEXT")
    private String about;
    private String headline;
    private String address;

}
