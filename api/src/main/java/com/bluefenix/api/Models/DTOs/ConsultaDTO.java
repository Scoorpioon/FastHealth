package com.bluefenix.api.Models.DTOs;

import java.time.LocalDateTime;

public class ConsultaDTO {
    private Long idConsulta;
    private LocalDateTime dataConsulta;
    private String tipoConsulta;
    private Long idPaciente;
    private Long idFila;
    
    public ConsultaDTO(Long idConsulta, LocalDateTime dataConsulta, String tipoConsulta, Long idPaciente, Long idFila) {
        this.idConsulta = idConsulta;
        this.dataConsulta = dataConsulta;
        this.tipoConsulta = tipoConsulta;
        this.idPaciente = idPaciente;
        this.idFila = idFila;
    }

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
}