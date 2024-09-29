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
}