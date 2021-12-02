package ru.itis.schoolApp.exception;

import ru.itis.schoolApp.services.validation.ErrorEntity;

public class NotFoundException extends ValidationException {
    public NotFoundException(String message) {
        super(ErrorEntity.NOT_FOUND, message);
    }
}
