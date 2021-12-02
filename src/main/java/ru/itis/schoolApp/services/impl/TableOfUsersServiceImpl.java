package ru.itis.schoolApp.services.impl;

import ru.itis.schoolApp.dao.UsersRepository;
import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.model.User;
import ru.itis.schoolApp.services.TableOfUsersService;

import java.util.List;
import java.util.stream.Collectors;

public class TableOfUsersServiceImpl implements TableOfUsersService {

    private final UsersRepository usersRepository;

    public TableOfUsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UserDto> getUsersSameRole(UserDto userDto) {
        if (userDto.getRole().equals("teacher")) {
            return usersRepository.findAllByRole("student").stream()
                    .map(UserDto::from)
                    .collect(Collectors.toList());
        }
        else return usersRepository.findAllByRole("teacher").stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
