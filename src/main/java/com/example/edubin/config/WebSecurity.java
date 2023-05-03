package com.example.edubin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableJpaAuditing
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurity {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOriginPatterns(List.of("*"));
                    configuration.setAllowedMethods(List.of("GET", "POST", "OPTIONS", "PUT", "DELETE", "PATCH"));
                    configuration.setAllowedHeaders(List.of("*"));
                    configuration.setAllowCredentials(true);
                    return configuration;
                }).and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers( "/**").permitAll()
                .requestMatchers("/api/category/**").permitAll()
                .requestMatchers("/api/category/list").permitAll()
                .requestMatchers("/api/employee/**").permitAll()
                .requestMatchers("/api/employee/listTeacher").permitAll()
                .requestMatchers("/api/course/**").permitAll()
                .requestMatchers("/api/course/list").permitAll()
                .requestMatchers("/api/course/recommend/list").permitAll()
                .requestMatchers("/api/category/get/**").permitAll()
                .requestMatchers("/api/content/**").permitAll()
                .requestMatchers("/api/content/list").permitAll()
                .requestMatchers("/api/comment/**").permitAll()
                .requestMatchers("/api/event/**").permitAll()
                .requestMatchers("/api/event/list").permitAll()
                .requestMatchers("/api/blog/**").permitAll()
                .requestMatchers("/api/blog/list/**").permitAll()
                .requestMatchers("/api/blog/one/**").permitAll()
                .requestMatchers("/api/blog/popular").permitAll()
                .requestMatchers("/api/employee/**").permitAll()
                .requestMatchers("/api/employee/list").permitAll()
                .requestMatchers("/api/employee/update/himself/**").permitAll()
                .requestMatchers("/api/email/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/product/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }
}
