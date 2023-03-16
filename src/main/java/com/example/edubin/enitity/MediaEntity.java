package com.example.edubin.enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private byte[] bytes;

    @OneToOne(cascade =CascadeType.REMOVE)
    private ContentEntity content;
}
