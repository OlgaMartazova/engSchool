package ru.itis.schoolApp.services.impl;

import ru.itis.schoolApp.dao.UsersRepository;
import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.dto.UserForm;
import ru.itis.schoolApp.model.User;
import ru.itis.schoolApp.services.EditProfileService;

public class EditProfileServiceImpl implements EditProfileService {
    private final UsersRepository usersRepository;

    public EditProfileServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDto edit(UserForm form, UserDto userDto) {
        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .role(form.getRole())
                .build();
        System.out.println(user.getFirstName());
        usersRepository.updateProfile(userDto.getId(), user);
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
