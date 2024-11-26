package com.bluefenix.api.Models.DTOs.SecurityDTOs;

import com.bluefenix.api.Models.Roles.UserRole;

public record SessaoPacienteDTO(String nome, String numCarteirinha, UserRole roles) {
    
}