package com.bluefenix.api.Models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fila")
public class Fila {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFila;

    @Column(name = "data_fila", nullable = false)
    private Date dataFila;

    @Column(name = "numero_consultas", nullable = false)
    private int numeroConsultas;

    @Column(name = "tempo_medio_consulta", nullable = false)
    private LocalTime tempoMedioConsulta;

    @ManyToMany
    @JoinTable(
        name = "fila_medico",
        joinColumns = @JoinColumn(name = "id_fila"),
        inverseJoinColumns = @JoinColumn(name = "id_medico"))
    private List<Medico> medicos;

    @ManyToMany
    @JoinTable(
        name = "pacientes_filas",
        joinColumns = @JoinColumn(name = "id_fila"),
        inverseJoinColumns = @JoinColumn(name = "id_paciente"))
    @JoinColumn(name = "pacientes", nullable = false)
    private List<Paciente> pacientes = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "atendentes", nullable = false)
    private List<Atendente> atendentes = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "consultas", nullable = false)
    private List<Consulta> consultas = new ArrayList<>();
}