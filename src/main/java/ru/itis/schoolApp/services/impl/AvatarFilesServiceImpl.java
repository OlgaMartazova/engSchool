package ru.itis.schoolApp.services.impl;

import ru.itis.schoolApp.dao.AvatarFilesRepository;
import ru.itis.schoolApp.dao.UsersRepository;
import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.exception.NotFoundException;
import ru.itis.schoolApp.model.AvatarFile;
import ru.itis.schoolApp.services.AvatarFilesService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public class AvatarFilesServiceImpl implements AvatarFilesService {

    private final String path;
    private final AvatarFilesRepository avatarFilesRepository;
    private final UsersRepository usersRepository;

    public AvatarFilesServiceImpl(String path, AvatarFilesRepository avatarFilesRepository, UsersRepository usersRepository) {
        this.path = path;
        this.avatarFilesRepository = avatarFilesRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public void readFileFromStorage(Long fileId, OutputStream outputStream) {
        Optional<AvatarFile> optionalFileInfo = avatarFilesRepository.findById(fileId);
        AvatarFile fileInfo = optionalFileInfo.orElseThrow(() -> new NotFoundException("File not found"));

        File file = new File(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);
        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public AvatarFile getFileInfo(Long fileId) {
        return avatarFilesRepository.findById(fileId).orElseThrow(() -> new NotFoundException("File not found"));
    }

    @Override
    public AvatarFile uploadAvatar(UserDto user, InputStream file, String originalFileName, String contentType, Long size) {
        AvatarFile avatar = new AvatarFile(
                null,
                originalFileName,
                UUID.randomUUID().toString(),
                size,
                contentType
        );
        try {
            Files.copy(file, Paths.get(path + avatar.getStorageFileName() + "." + avatar.getType().split("/")[1]));
            avatar = avatarFilesRepository.saveUpdate(avatar);
                    usersRepository.updateAvatarForUser(user.getId(), avatar.getFileId());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return avatar;
    }
}
