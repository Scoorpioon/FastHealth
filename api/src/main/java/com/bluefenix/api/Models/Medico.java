package com.bluefenix.api.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedico;

    @Column(name = "crm", columnDefinition = "CHAR(6)", nullable = false)
    private String crm;

    @Column(name = "sigla_crm", columnDefinition = "CHAR(2)", nullable = false)
    private String siglaCRM;

    @ManyToMany(mappedBy = "medicos")
    private List<Fila> filas = new ArrayList<>();

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getSiglaCRM() {
        return siglaCRM;
    }

    public void setSiglaCRM(String siglaCRM) {
        this.siglaCRM = siglaCRM;
    }

    public List<Fila> getFilas() {
        return filas;
    }

    public void setFilas(List<Fila> filas) {
        this.filas = filas;
    }
}