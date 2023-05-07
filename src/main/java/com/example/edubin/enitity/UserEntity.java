package com.example.edubin.enitity;

import com.example.edubin.dto.request.UserRegister;
import com.example.edubin.enitity.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonIgnore
    @NotBlank
    private String password;
    @JsonIgnore
    private LocalDate registeredDate;
    private String name;
    private LocalDate birthDay;
    private String picture;
    private String profession;
    @Column(columnDefinition="TEXT")
    private String about;
    @Column(columnDefinition="TEXT")
    private String achievement;
    @Column(columnDefinition="TEXT")
    private String myObjective;

    @ManyToMany( cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private List<MerchandiseEntity> merchandiseList;
    @JsonManagedReference
    @OneToMany(mappedBy = "teacher",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<CommentEntity> comments;

    @NotBlank
    @Column(unique = true)
    private String email;

//    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    private SocialMediaEntity socialMedia;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> permission;

    @JsonIgnore
    public static UserEntity from(UserRegister userRegister){
        return UserEntity.builder()
                .name(userRegister.getName())
                .email(userRegister.getEmail())
                .password(userRegister.getPassword())
                .username(userRegister.getUsername())
                .registeredDate(LocalDate.now())
                .roles(List.of("USER"))
                .build();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (roles!=null && roles.size()!=0 ){
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
