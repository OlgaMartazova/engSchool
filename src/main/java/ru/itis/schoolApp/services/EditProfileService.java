package ru.itis.schoolApp.services;

import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.dto.UserForm;

public interface EditProfileService {
    UserDto edit(UserForm userForm, UserDto userDto);
}
