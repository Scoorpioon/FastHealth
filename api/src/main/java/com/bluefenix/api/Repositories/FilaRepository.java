package com.bluefenix.api.Repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bluefenix.api.Models.Fila;
import com.bluefenix.api.Models.Paciente;

@Repository
public interface FilaRepository extends JpaRepository<Fila, Long> {
    Fila findByDataFila(Date data); // Procurar a fila pela data
}