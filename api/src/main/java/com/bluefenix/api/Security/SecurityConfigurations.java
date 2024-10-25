package com.bluefenix.api.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
/*             .requestMatchers(HttpMethod.POST, "/atendente").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/auth/atendente/cadastro").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/atendente/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/paciente/cadastro").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/auth/paciente/login").permitAll()
            .requestMatchers(HttpMethod.GET, "/pacientes/buscarTodosPacientes").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/pacientes/criar").permitAll()
            .requestMatchers(HttpMethod.POST, "/consultas/criar").permitAll()
            .requestMatchers(HttpMethod.GET, "/consultas/buscarConsultas").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/fila/encontrar/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/fila/todasAsFilas").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/fila/criar").hasRole("ADMIN") */
            .anyRequest().permitAll()
        )
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}