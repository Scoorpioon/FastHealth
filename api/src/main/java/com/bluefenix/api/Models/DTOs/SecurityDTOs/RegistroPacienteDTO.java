package com.bluefenix.api.Models.DTOs.SecurityDTOs;

import java.time.LocalDate;

import com.bluefenix.api.Models.Roles.UserRole;

public record RegistroPacienteDTO(
    String nome, 
    String cpf, 
    LocalDate nascimento, 
    String numCarteirinha, 
    String rg,
    String email,
    String senha,
    int pcd,
    UserRole roles
) {
    
}