package com.bluefenix.api.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bluefenix.api.Models.Consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query(value = "SELECT * FROM consulta WHERE data_consulta = ?1 ORDER BY horario_consulta", nativeQuery = true)
    List<Consulta> findByDataConsulta(LocalDate data);

    @Query(value = "SELECT c FROM Consulta c WHERE c.dataHorarioConsulta = ?1")
    Consulta verificarExistenciaDeConsulta(LocalDateTime dataHorario);
}