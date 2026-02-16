package me.authapp.enumpackage;

public enum UserRole {
    GUEST,
    USER,
    ADMIN;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
