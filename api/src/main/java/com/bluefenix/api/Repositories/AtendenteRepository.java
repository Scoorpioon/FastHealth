package com.bluefenix.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.bluefenix.api.Models.Atendente;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
    UserDetails findByNome(String nome);
}