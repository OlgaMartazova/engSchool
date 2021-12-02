package ru.itis.schoolApp.services;

import ru.itis.schoolApp.dto.UserDto;

import java.util.List;

public interface TableOfUsersService {
    List<UserDto> getUsersSameRole (UserDto userDto);
}
