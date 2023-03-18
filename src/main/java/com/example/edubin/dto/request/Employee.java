package com.example.edubin.dto.request;

import com.example.edubin.enitity.role.Permission;
import com.example.edubin.enitity.role.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String name;
    @NotBlank
    private String username;
    private String password;
    @NotBlank
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private List<String> roles;
    private List<String> permissionList;
}
