package com.bluefenix.api.Models.DTOs.MetodosWS;

import java.time.LocalDate;

public class BuscarFilaPorDataRequest {
    private LocalDate dataFila;

    public LocalDate getDataFila() {
        return dataFila;
    }

    public void setDataFila(LocalDate dataFila) {
        this.dataFila = dataFila;
    }
}