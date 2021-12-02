package ru.itis.schoolApp.services.validation;

import ru.itis.schoolApp.dto.UserForm;

import java.util.Optional;

public interface Validator {
    Optional<ErrorEntity> validateRegistration(UserForm form);
}
