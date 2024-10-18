package com.bluefenix.api.Models.DTOs.MetodosWS;

public class RemoverConsultaRequest {
    private Long idConsulta;
    private Long idFila;

    public Long getIdConsulta() {
        return idConsulta;
    }
    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }
    public Long getIdFila() {
        return idFila;
    }
    public void setIdFila(Long idFila) {
        this.idFila = idFila;
    }
}