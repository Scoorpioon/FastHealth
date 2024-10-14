package com.bluefenix.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluefenix.api.Models.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    
}