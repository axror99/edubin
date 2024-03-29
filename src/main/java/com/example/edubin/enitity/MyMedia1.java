package com.example.edubin.enitity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MyMedia1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fileOriginalName; // pdp.uz
    private long size;  //1024
    private String contentType;  // application/pdf/image
    private  byte[] bytes;
    private String  name;
}
