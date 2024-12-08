package com.bluefenix.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluefenix.api.Models.Login;

public interface LoginRepository extends JpaRepository<Login, String> {
    
}