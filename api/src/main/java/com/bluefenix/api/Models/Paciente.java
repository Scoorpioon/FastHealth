package com.bluefenix.api.Models;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bluefenix.api.Models.Roles.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "paciente")
public class Paciente implements UserDetails, Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    @Column(name = "nome", length = 40, nullable = false)
    private String nome;

    @Column(name = "cpf", columnDefinition = "CHAR(11)", nullable = false)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate nascimento;

    @Column(name = "numero_carteirinha", length = 15, nullable = false)
    private String numCarteirinha;

    @Column(name = "rg", length = 9, nullable = false)
    private String rg;

    @Column (name = "email", length = 60, nullable = false, unique = true)
    private String email;

    @Column (name = "senha", length = 60, nullable = false)
    private String senha;

    @Column (name = "paciente_pcd", columnDefinition = "BIT", nullable = false)
    private int pcd;

    @Column(columnDefinition = "CHAR(5)")
    private UserRole roles;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"fila", "paciente"}) // Não precisamos saber em qual fila a consulta está e nem repetir a informação de paciente
    private Set<Consulta> consultas;

    
    public Paciente(String nome, String cpf, LocalDate nascimento, String numCarteirinha, String rg, String email,
            String senha, int pcd, UserRole roles) {
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.numCarteirinha = numCarteirinha;
        this.rg = rg;
        this.email = email;
        this.senha = senha;
        this.pcd = pcd;
        this.roles = roles;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getNumCarteirinha() {
        return numCarteirinha;
    }

    public void setNumCarteirinha(String numCarteirinha) {
        this.numCarteirinha = numCarteirinha;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPcd() {
        return pcd;
    }

    public void setPcd(int pcd) {
        this.pcd = pcd;
    }

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }

    public UserRole getRoles() {
        return roles;
    }

    public void setRoles(UserRole roles) {
        this.roles = roles;
    }
}