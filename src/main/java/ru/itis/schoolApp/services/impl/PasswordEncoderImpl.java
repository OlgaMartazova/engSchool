package ru.itis.schoolApp.services.impl;

import ru.itis.schoolApp.services.PasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public boolean matches(String password, int hashPassword) {
        return hashPassword == encode(password);
    }

    @Override
    public int encode(String password) {
        return password.hashCode();
    }
}
