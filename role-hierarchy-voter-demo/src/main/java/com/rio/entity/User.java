package com.rio.entity;

public record User(String email, String password, Role role) {

    public enum Role {
        ADMIN, EMPLOYEE, GUEST
    }
}