package com.bluefenix.api.Models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;

    @OneToOne
    @JoinColumn(name = "medico", nullable = false)
    private Medico medico;

    @Column(name = "data_consulta", nullable = false)
    private Date dataConsulta;

    @Column(name = "tipo_consulta", length = 16, nullable = false)
    private String tipoConsulta;
}