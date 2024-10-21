package com.bluefenix.api.Models.DTOs.SecurityDTOs;

import com.bluefenix.api.Models.Roles.UserRole;

public record AtRegisterDTO(String email, String nome, String cpf, String senha, UserRole roles) {
    
}