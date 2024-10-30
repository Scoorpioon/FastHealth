package com.bluefenix.api.Models.DTOs;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class ConsultaDTO {
    private Long idConsulta;
    private LocalDate dataConsulta;
    private LocalDateTime dataHorarioConsulta;
    private String tipoConsulta;
    private Long idPaciente;
    private Long idFila;
    private int consultaRealizada;

    public ConsultaDTO(Long idConsulta, LocalDate dataConsulta, LocalDateTime dataHorarioConsulta, String tipoConsulta, Long idPaciente, int consultaRealizada) {
        this.idConsulta = idConsulta;
        this.dataConsulta = dataConsulta;
        this.dataHorarioConsulta = dataHorarioConsulta;
        this.tipoConsulta = tipoConsulta;
        this.idPaciente = idPaciente;
        this.consultaRealizada = consultaRealizada;
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

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalDateTime getDataHorarioConsulta() {
        return dataHorarioConsulta;
    }

    public void setDataHorarioConsulta(LocalDateTime dataHorarioConsulta) {
        this.dataHorarioConsulta = dataHorarioConsulta;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdFila() {
        return idFila;
    }

    public void setIdFila(Long idFila) {
        this.idFila = idFila;
    }

    public int getConsultaRealizada() {
        return consultaRealizada;
    }

    public void setConsultaRealizada(int consultaRealizada) {
        this.consultaRealizada = consultaRealizada;
    }
}