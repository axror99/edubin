package com.example.edubin.enitity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MediaContentEntity1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private byte[] videoBytes;
    private byte[] fileBytes;

    @OneToOne
    private ContentEntity1 content;
}
