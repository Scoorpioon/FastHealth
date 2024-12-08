package com.bluefenix.api.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dados_hospital")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idHospital;

    @Column(name = "nome_fantasia", length = 32, nullable = false)
    private String nome;

    @Column(name = "razao_social", length = 40, unique = true, nullable = false)
    private String razaoSocial;

    @Column(name = "cnpj", length = 14, unique = true, nullable = false)
    private String cnpj;

    @Column(name = "tipo_hospital", length = 12)
    private String tipo;

    @Column(name = "responsavel_tecnico", length = 40)
    private String responsavel;

    // private String especialidades; Depois eu vou fazer uma tabela à parte

    @Column(name = "status_hospital", nullable = false)
    private boolean status;

    public String getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(String idHospital) {
        this.idHospital = idHospital;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}