package ru.itis.schoolApp.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Lesson {
    private Long lessonId;
    private User author;
    private String content;
    private Timestamp createdAt;
    private String subject;
    private String forWhom;
}
