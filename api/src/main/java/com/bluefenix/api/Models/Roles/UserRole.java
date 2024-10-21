package com.bluefenix.api.Models.Roles;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserRole {

    @JsonProperty("admin")
    ADMIN("admin"),
    
    @JsonProperty("user")
    USER("user");

    private String role;

    UserRole(String i) {
        this.role = i;
    }

    public String getRole() {
        return this.role;
    }
}