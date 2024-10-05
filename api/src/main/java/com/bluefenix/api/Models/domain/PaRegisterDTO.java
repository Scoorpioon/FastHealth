package com.bluefenix.api.Models.domain;

import java.util.Date;

public record PaRegisterDTO(String nome, String cpf, Date nascimento, String numCarteirinha,
String rg, String email, String senha, int pcd, String roles) {
    
}