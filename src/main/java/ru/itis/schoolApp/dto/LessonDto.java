package ru.itis.schoolApp.dto;

import lombok.*;
import ru.itis.schoolApp.model.Lesson;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {
    private Long id;
    private UserDto author;
    private String content;
    private Timestamp createdAt;
    private String subject;
    private String forWhom;

    public static LessonDto from(Lesson lesson) {
        return LessonDto.builder()
                .id(lesson.getLessonId())
                .author(UserDto.from(lesson.getAuthor()))
                .content(lesson.getContent())
                .createdAt(lesson.getCreatedAt())
                .subject(lesson.getSubject())
                .forWhom(lesson.getForWhom())
                .build();
    }
}
