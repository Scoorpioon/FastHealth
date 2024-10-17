package com.bluefenix.api.Models.DTOs;

import java.time.LocalDate;

import java.util.Set;

public class PacienteDTO {
    private Long idPaciente;
    private String nome;
    private String cpf;
    private LocalDate nascimento;
    private String numCarteirinha;
    private String rg;
    private String email;
    private String senha;
    private Set<Long> consultas;

    public PacienteDTO(Long idPaciente, String nome, String cpf, LocalDate nascimento, String numCarteirinha, String rg,
            String email, String senha, Set<Long> consultas) {
        this.idPaciente = idPaciente;
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.numCarteirinha = numCarteirinha;
        this.rg = rg;
        this.email = email;
        this.senha = senha;
        this.consultas = consultas;
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

    public Set<Long> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<Long> consultas) {
        this.consultas = consultas;
    }
}