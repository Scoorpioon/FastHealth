package com.bluefenix.api.Models.DTOs.SecurityDTOs;

import com.bluefenix.api.Models.Roles.UserRole;

public record SessaoAtendenteDTO(String email, String roles, String token) {
    
}