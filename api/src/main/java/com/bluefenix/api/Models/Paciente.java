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
@Table(name = "paciente")
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    @Column(name = "nome", length = 40, nullable = false)
    private String nome;

    @Column(name = "cpf", columnDefinition = "CHAR(11)", nullable = false)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private Date nascimento;

    @Column(name = "numero_carteirinha", length = 15, nullable = false)
    private Date numCarteirinha;

    @Column(name = "rg", length = 9, nullable = false)
    private String rg;

    @Column (name = "email", length = 60, nullable = false, unique = true)
    private String email;

    @Column (name = "senha", length = 25, nullable = false)
    private String senha;

    @Column (name = "paciente_pcd", columnDefinition = "BIT", nullable = false)
    private int pcd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column (name = "datareg", nullable = false)
    private Date registerDate;

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

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Date getNumCarteirinha() {
        return numCarteirinha;
    }

    public void setNumCarteirinha(Date numCarteirinha) {
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

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}