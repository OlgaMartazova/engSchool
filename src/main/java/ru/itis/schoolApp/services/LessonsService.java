package ru.itis.schoolApp.services;

import ru.itis.schoolApp.dto.LessonDto;
import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.model.Lesson;

import java.util.List;

public interface LessonsService {
    List<LessonDto> getByAuthorId(Long authorId);
    LessonDto addLesson(LessonDto lessonDto);
    List<LessonDto> getLessons();
    List<LessonDto> getByForWhom(String forWhom);
    List<LessonDto> getBySubject(String subject);
    LessonDto getLesson(Long lessonId);
    UserDto getAuthor(LessonDto lesson);
}
