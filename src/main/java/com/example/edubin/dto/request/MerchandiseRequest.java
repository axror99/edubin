package com.example.edubin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MerchandiseRequest {
    private String headline;
    private Double price;
    private MultipartFile picture;
    private String definition;
}
