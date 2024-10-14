package com.bluefenix.api.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluefenix.api.Models.Paciente;
import com.bluefenix.api.Models.Fila;
import java.util.Set;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByCpf(String cpf);

    List<Paciente> findByFilas(Set<Fila> filas);
}