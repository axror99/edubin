package com.example.edubin.config.data;

import com.example.edubin.enitity.UserEntity;
import com.example.edubin.enitity.role.Permission;
import com.example.edubin.enitity.role.Role;
import com.example.edubin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.results.graph.Initializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail("superAdmin@gmail.com")){
            UserEntity user= new UserEntity();
            user.setName("Super_Admin_Bek");
            user.setEmail("superAdmin@gmail.com");
            user.setPassword(passwordEncoder.encode("superAdmin"));
            user.setBirthDay(LocalDate.of(1999,6,7));
            user.setRegisteredDate(LocalDate.of(2023,3,16));
            user.setUsername("superAdmin");
            user.setRoles(Collections.singletonList(Role.SUPER_ADMIN.name()));
            user.setPermission(Arrays.asList(
                    Permission.ADD.name(),
                    Permission.READ.name(),
                    Permission.DELETE.name(),
                    Permission.UPDATE.name()
            ));
            userRepository.save(user);
        }
    }
}
