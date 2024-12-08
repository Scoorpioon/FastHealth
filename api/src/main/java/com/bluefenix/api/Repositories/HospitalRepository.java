package com.bluefenix.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluefenix.api.Models.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, String> {
    
}