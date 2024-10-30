package com.bluefenix.api.Models.DTOs.MetodosWS;

import java.time.LocalDate;

public class BuscarConsultaPorDataRequest {
    private LocalDate dataConsulta;

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
}