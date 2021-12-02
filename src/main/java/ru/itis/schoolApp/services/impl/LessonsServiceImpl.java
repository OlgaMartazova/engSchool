package ru.itis.schoolApp.services.impl;

import ru.itis.schoolApp.dao.LessonsRepository;
import ru.itis.schoolApp.dao.UsersRepository;
import ru.itis.schoolApp.dto.LessonDto;
import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.model.Lesson;
import ru.itis.schoolApp.model.User;
import ru.itis.schoolApp.services.LessonsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LessonsServiceImpl implements LessonsService {
    private final LessonsRepository lessonsRepository;
    private final UsersRepository usersRepository;

    public LessonsServiceImpl(LessonsRepository lessonsRepository, UsersRepository usersRepository) {
        this.lessonsRepository = lessonsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<LessonDto> getByAuthorId(Long authorId) {
        return lessonsRepository.findByAuthorId(authorId).stream()
                .map(LessonDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public LessonDto addLesson(LessonDto lessonDto) {
        Lesson lesson = Lesson.builder()
                .lessonId(lessonDto.getId())
                .author(User.builder()
                        .userId(lessonDto.getAuthor().getId())
                        .avatarId(lessonDto.getAuthor().getAvatarId())
                        .email(lessonDto.getAuthor().getEmail())
                        .firstName(lessonDto.getAuthor().getFirstName())
                        .lastName(lessonDto.getAuthor().getLastName())
                        .build())
                .content(lessonDto.getContent())
                .createdAt(lessonDto.getCreatedAt())
                .subject(lessonDto.getSubject())
                .forWhom(lessonDto.getForWhom())
                .build();
        Lesson savedLesson = lessonsRepository.saveUpdate(lesson);
        return LessonDto.from(savedLesson);
    }

    @Override
    public List<LessonDto> getLessons() {
        return lessonsRepository.findAll().stream()
                .map(LessonDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<LessonDto> getByForWhom(String forWhom) {
        return lessonsRepository.findByForWhom(forWhom).stream()
                .map(LessonDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<LessonDto> getBySubject(String subject) {
        return lessonsRepository.findBySubject(subject).stream()
                .map(LessonDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public LessonDto getLesson(Long lessonId) {
        return lessonsRepository.findById(lessonId).stream()
                .map(LessonDto::from)
                .findFirst()
                .get();
    }

    @Override
    public UserDto getAuthor(LessonDto lesson) {
        return usersRepository.findById(lesson.getAuthor().getId()).stream()
                .map(UserDto::from)
                .findFirst()
                .get();
    }
}
