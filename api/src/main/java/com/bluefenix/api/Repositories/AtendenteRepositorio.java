package com.bluefenix.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluefenix.api.Models.Atendente;

@Repository
public interface AtendenteRepositorio extends JpaRepository<Atendente, Long> {

}