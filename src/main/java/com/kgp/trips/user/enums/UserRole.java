package com.kgp.trips.user.enums;

public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    String name;
    UserRole(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
