package ru.itis.schoolApp.services.impl;

import ru.itis.schoolApp.dao.UsersRepository;
import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.dto.UserForm;
import ru.itis.schoolApp.exception.ValidationException;
import ru.itis.schoolApp.model.User;
import ru.itis.schoolApp.services.PasswordEncoder;
import ru.itis.schoolApp.services.SignInService;
import ru.itis.schoolApp.services.validation.ErrorEntity;

import java.util.UUID;

public class SignInServiceImpl implements SignInService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto signIn(UserForm userForm) {
        System.out.println("тут был 1");
        User user = usersRepository.findByEmail(userForm.getEmail())
                .orElseThrow(() -> new ValidationException(ErrorEntity.NOT_FOUND));
        if (!passwordEncoder.matches(userForm.getPassword(), user.getHashPassword())) {
            throw new ValidationException(ErrorEntity.INCORRECT_PASSWORD);
        }
        UserDto userDto = UserDto.from(user);
        String token = UUID.randomUUID().toString();
        if(usersRepository.getTokenByUserId(user.getUserId()).isPresent()) {
            usersRepository.updateTokenForUser(user.getUserId(), token);
        } else {
            usersRepository.createTokenForUser(user.getUserId(), token);
        }
        userDto.setToken(token);
        System.out.println("тут был 2");
        return userDto;
    }

    @Override
    public UserDto signIn(String token) {
        User user = usersRepository.findByToken(token)
                .orElseThrow(() -> new ValidationException(ErrorEntity.NOT_FOUND));
        return UserDto.from(user);
    }
}
