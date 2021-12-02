package ru.itis.schoolApp.services.impl;

import ru.itis.schoolApp.dao.UsersRepository;
import ru.itis.schoolApp.dto.UserForm;
import ru.itis.schoolApp.exception.ValidationException;
import ru.itis.schoolApp.model.User;
import ru.itis.schoolApp.services.PasswordEncoder;
import ru.itis.schoolApp.services.SignUpService;
import ru.itis.schoolApp.services.validation.ErrorEntity;
import ru.itis.schoolApp.services.validation.Validator;

import java.util.Optional;

public class SignUpServiceImpl implements SignUpService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    public SignUpServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, Validator validator) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }
    @Override
    public void signUp(UserForm form) {
        Optional<ErrorEntity> optionalError = validator.validateRegistration(form);
        if(optionalError.isPresent()) {
            throw new ValidationException(optionalError.get());
        }
        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .role(form.getRole())
                .build();
        usersRepository.saveUpdate(user);
    }
}
