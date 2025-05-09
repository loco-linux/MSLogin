package com.duoc.MSLogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // importante para permitir POST desde HTML sin token CSRF
            .cors(cors ->{}) // Habilita el CORS definido en WebCorsConfig
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/auth/**", "/auth.html", "/auth.css", "/static/**").permitAll()
                .anyRequest().authenticated()
            )
            .build();
    }
}
