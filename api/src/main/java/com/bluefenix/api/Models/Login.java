package com.bluefenix.api.Models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bluefenix.api.Models.Roles.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "login")
public class Login implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "email", length = 40, nullable = false, unique = true)
    private String email;

    @Column(name = "senha", length = 65, nullable = false)
    private String senha;

    @Column(name = "roles")
    private UserRole roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAtendente")
    private Atendente atendente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;

    public Login(String email, String senha, Atendente atendente, UserRole roles) {
        this.email = email;
        this.senha = senha;
        this.atendente = atendente;
        this.roles = roles;
    }

    public Login(String email, String senha, Paciente paciente, UserRole roles) {
        this.email = email;
        this.senha = senha;
        this.paciente = paciente;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}