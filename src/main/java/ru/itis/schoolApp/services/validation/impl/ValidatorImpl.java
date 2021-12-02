package ru.itis.schoolApp.services.validation.impl;

import ru.itis.schoolApp.dao.UsersRepository;
import ru.itis.schoolApp.dto.UserForm;
import ru.itis.schoolApp.services.validation.ErrorEntity;
import ru.itis.schoolApp.services.validation.Validator;

import java.util.Optional;

public class ValidatorImpl implements Validator {
    private final UsersRepository usersRepository;

    public ValidatorImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<ErrorEntity> validateRegistration(UserForm form) {
        if (form.getEmail() == null) {
            return Optional.of(ErrorEntity.INVALID_EMAIL);
        } else if(usersRepository.findByEmail(form.getEmail()).isPresent()) {
            return Optional.of(ErrorEntity.EMAIL_ALREADY_TAKEN);
        }
        return Optional.empty();
    }
}
