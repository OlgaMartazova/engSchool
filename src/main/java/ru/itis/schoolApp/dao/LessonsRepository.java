package ru.itis.schoolApp.dao;

import ru.itis.schoolApp.dao.base.CrudRepository;
import ru.itis.schoolApp.model.Lesson;
import ru.itis.schoolApp.model.User;

import java.util.List;

public interface LessonsRepository extends CrudRepository<Lesson, Long> {
    List<Lesson> findByAuthorId(Long authorId);
    List<Lesson> findBySubject(String subject);
    List<Lesson> findByForWhom(String forWhom);
}
