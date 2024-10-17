package com.bluefenix.api.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idConsulta", scope = Consulta.class)
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;
    
    // Por enquanto, vamos inserir o horário da consulta na data mesmo
    @Column(name = "data_horario_consulta", nullable = false)
    private LocalDateTime dataConsulta;
    
    @Column(name = "tipo_consulta", length = 16, nullable = false)
    private String tipoConsulta;
    

    // Ainda não criei o path de criação de médico, então vai ficar comentado por enquanto
/*     @OneToOne
    @JoinColumn(name = "medico", nullable = false)
    private Medico medico; */

    @ManyToOne
    @JoinColumn(name = "id_fila")
    private Fila fila;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

/*     public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    } */

    public Fila getFila() {
        return fila;
    }

    public void setFila(Fila fila) {
        this.fila = fila;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}