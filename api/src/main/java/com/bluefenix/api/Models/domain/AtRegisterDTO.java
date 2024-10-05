package com.bluefenix.api.Models.domain;

public record AtRegisterDTO(String cpf, String nome, String email, String senha, UserRole roles) {
    
}