package ru.itis.schoolApp.services;

public interface PasswordEncoder {
    boolean matches(String password, int hashPassword);
    int encode(String password);
}
