package com.example.edubin.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {

    private String name;
    private long card;
    @Email
    private String email;
    private String expiredDate;
}
