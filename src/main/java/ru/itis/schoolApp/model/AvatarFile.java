package ru.itis.schoolApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AvatarFile {
    private Long fileId;
    private String originalFileName;
    private String storageFileName;
    private Long size;
    private String type;
}
