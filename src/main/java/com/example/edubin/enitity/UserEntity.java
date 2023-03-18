package com.example.edubin.enitity;

import com.example.edubin.dto.request.UserRegister;
import com.example.edubin.enitity.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    private String password;

    private String name;
    private LocalDate birthDay;

    @NotBlank
    @Column(unique = true)
    private String email;
    @ElementCollection
    private List<String> roles;

    @ElementCollection
    private List<String> permission;

    @JsonIgnore
    public static UserEntity from(UserRegister userRegister){
        return UserEntity.builder()
                .name(userRegister.getName())
                .email(userRegister.getEmail())
                .password(userRegister.getPassword())
                .username(userRegister.getUsername())
                .build();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (roles!=null){
            roles.forEach((role)-> authorityList.add(new SimpleGrantedAuthority("ROLE_"+role)));
        }else {
            authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        if (permission!=null){
            permission.forEach((permiss)-> authorityList.add(new SimpleGrantedAuthority(permiss)));
        }

        return authorityList;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
