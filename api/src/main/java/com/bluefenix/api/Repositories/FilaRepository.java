package com.bluefenix.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluefenix.api.Models.Fila;

import java.time.LocalDate;

@Repository
public interface FilaRepository extends JpaRepository<Fila, Long> {
    Fila findByDataFila(LocalDate data); // Procurar a fila pela data
}