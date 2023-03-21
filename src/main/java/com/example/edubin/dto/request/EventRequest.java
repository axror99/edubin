package com.example.edubin.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    private MultipartFile picture;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime finishTime;
    private String about;
    private String headline;
    private String address;
}
