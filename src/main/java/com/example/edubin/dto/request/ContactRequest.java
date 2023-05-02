package com.example.edubin.dto.request;

import lombok.Data;

@Data
public class ContactRequest {
    private String name;
    private String email;
    private String subject;
    private String phone;
    private String text;
}
