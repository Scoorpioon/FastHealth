package com.bluefenix.api.Models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "atendente")
public class Atendente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtendente;

    @Column(name = "cpf", columnDefinition = "CHAR(11)", nullable = false, unique = true)
    private String cpf;

    @Column(name = "nome", length = 40, nullable = false)
    private String nome;

    @Column (name = "email", length = 60, nullable = false, unique = true)
    private String email;

    @Column (name = "senha", length = 25, nullable = false)
    private String senha;

    @Temporal(TemporalType.TIMESTAMP)
    @Column (name = "datareg", nullable = false)
    private Date registerDate;

    public Long getIdAtendente() {
        return idAtendente;
    }

    public void setIdAtendente(Long idAtendente) {
        this.idAtendente = idAtendente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}