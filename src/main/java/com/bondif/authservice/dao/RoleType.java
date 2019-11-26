package com.bondif.authservice.dao;

public enum RoleType {
    USER("USER"), ADMIN("ADMIN"), ANALYSE_MANAGER("ANALYSE_MANAGER");

    private String role;

    private RoleType(String role) {
        this.role = role;
    }
}
