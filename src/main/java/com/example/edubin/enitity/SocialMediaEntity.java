package com.example.edubin.enitity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SocialMediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String telegram;
    private String instagram;
    private String google;
    private String linkedIn;
    private String facebook;
    private String twitter;
//    @JsonBackReference
//    @OneToOne(fetch = FetchType.LAZY)
//    private UserEntity user;
}
