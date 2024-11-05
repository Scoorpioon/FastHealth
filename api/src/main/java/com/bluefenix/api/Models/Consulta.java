package com.bluefenix.api.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;
    
    @Column(name = "data_consulta", nullable = false)
    private LocalDate dataConsulta;

    @Column(name = "horario_consulta", nullable = false)
    private LocalDateTime dataHorarioConsulta;

    @Column(name = "tipo_consulta", length = 25, nullable = false)
    private String tipoConsulta;

    @Column(name = "consulta_realizada")
    private Boolean consultaRealizada = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_fila")
    private Fila fila;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id")
    @JsonIgnoreProperties("consultas")
    private Paciente paciente;

    public Consulta(Paciente paciente, Fila fila, LocalDate dataConsulta, LocalDateTime horarioConsulta, String tipoConsulta) {
        this.paciente = paciente;
        this.fila = fila;
        this.dataConsulta = dataConsulta;
        this.dataHorarioConsulta = horarioConsulta;
        this.tipoConsulta = tipoConsulta;
    }

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public LocalDateTime getDataHorarioConsulta() {
        return dataHorarioConsulta;
    }

    public void setDataHorarioConsulta(LocalDateTime dataHorarioConsulta) {
        this.dataHorarioConsulta = dataHorarioConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public Boolean getConsultaRealizada() {
        return consultaRealizada;
    }

    public void setConsultaRealizada(Boolean consultaRealizada) {
        this.consultaRealizada = consultaRealizada;
    }

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