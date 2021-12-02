package ru.itis.schoolApp.services;

import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.dto.UserForm;

public interface SignInService {
    UserDto signIn(UserForm userForm);
    UserDto signIn(String token);
}
