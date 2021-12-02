package ru.itis.schoolApp.services;

import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.model.AvatarFile;

import java.io.InputStream;
import java.io.OutputStream;

public interface AvatarFilesService {
    void readFileFromStorage(Long fileId, OutputStream outputStream);
    AvatarFile getFileInfo(Long fileId);
    AvatarFile uploadAvatar(UserDto user, InputStream file, String originalFileName, String contentType, Long size);
}
