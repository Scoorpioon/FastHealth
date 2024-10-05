package com.bluefenix.api.Models.domain;

public enum UserRole {
    ADMIN("atendente"),
    USER("paciente");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}