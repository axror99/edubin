package com.example.edubin.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
