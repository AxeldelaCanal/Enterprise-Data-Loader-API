package com.axel.masivo_tiendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Para tu caso (form + fetch simple), es práctico desactivar CSRF.
            // Más adelante podemos activarlo si querés hacerlo más estricto.
            .csrf(csrf -> csrf.disable())

            // Todo requiere login
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )

            // Login form default
            .formLogin(Customizer.withDefaults())

            // Logout default
            .logout(Customizer.withDefaults());

        return http.build();
    }
}
