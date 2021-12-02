package ru.itis.schoolApp.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.schoolApp.dao.LessonsRepository;
import ru.itis.schoolApp.model.Lesson;
import ru.itis.schoolApp.model.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class LessonsRepositoryImpl implements LessonsRepository {

    private final static String SQL_INSERT = "insert into lessons(author_id, created_at, content, subject, for_whom) " +
            "values (?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "update lessons set author_id = ?, created_at = ?, content = ?, subject = ?, for_whom = ? where lesson_id = ?";
    private final static String SQL_SELECT_BY_ID = "select lesson_id, author_id, created_at, content, subject, for_whom, user_id, first_name, last_name, email, password_hash, role, avatar_id from lessons left join users on lessons.author_id = users.user_id where lessons.lesson_id = ?";
    private final static String SQL_SELECT_ALL = "select lesson_id, author_id, created_at, content, subject, for_whom, user_id, first_name, last_name, email, password_hash, role, avatar_id from lessons left join users on lessons.author_id = users.user_id order by created_at desc";
    private final static String SQL_DELETE_BY_ID = "delete from posts where post_id = ?";
    private final static String SQL_SELECT_BY_AUTHOR_ID = "select lesson_id, author_id, created_at, content, subject, for_whom, user_id, first_name, last_name, email, password_hash, role, avatar_id from lessons left join users on lessons.author_id = users.user_id where users.user_id = ? order by created_at desc";
    private static final String SQL_SELECT_BY_SUBJECT = "select lesson_id, author_id, created_at, content, subject, for_whom, user_id, first_name, last_name, email, password_hash, role, avatar_id from lessons left join users on lessons.author_id = users.user_id where lessons.subject = ?";
    private final static String SQL_SELECT_BY_FOR_WHOM = "select lesson_id, author_id, created_at, content, subject, for_whom, user_id, first_name, last_name, email, password_hash, role, avatar_id from lessons left join users on lessons.author_id = users.user_id where lessons.for_whom = ?";

    private final RowMapper<Lesson> rowMapper = (row, rowNumber) ->
            Lesson.builder()
            .lessonId(row.getLong("lesson_id"))
            .author(
                    User.builder()
                            .userId(row.getLong("user_id"))
                            .firstName(row.getString("first_name"))
                            .lastName(row.getString("last_name"))
                            .email(row.getString("email"))
                            .hashPassword(row.getInt("password_hash"))
                            .role(row.getString("role"))
                            .avatarId(row.getLong("avatar_id"))
                            .build()
            )
            .createdAt(row.getTimestamp("created_at"))
            .content(row.getString("content"))
                    .subject(row.getString("subject"))
                    .forWhom(row.getString("for_whom"))
            .build();

    private final JdbcTemplate jdbcTemplate;

    public LessonsRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Lesson> findByAuthorId(Long authorId) {
        return jdbcTemplate.query(SQL_SELECT_BY_AUTHOR_ID, rowMapper, authorId);
    }

    @Override
    public List<Lesson> findBySubject(String subject) {
        return jdbcTemplate.query(SQL_SELECT_BY_SUBJECT, rowMapper, subject);
    }

    @Override
    public List<Lesson> findByForWhom(String forWhom) {
        return jdbcTemplate.query(SQL_SELECT_BY_FOR_WHOM, rowMapper, forWhom);
    }

    @Override
    public Optional<Lesson> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Lesson> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @Override
    public Lesson saveUpdate(Lesson item) {
        if(item.getLessonId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, new String[]{"lesson_id"});
                preparedStatement.setLong(1, item.getAuthor().getUserId());
                preparedStatement.setTimestamp(2, item.getCreatedAt());
                preparedStatement.setString(3, item.getContent());
                preparedStatement.setString(4, item.getSubject());
                preparedStatement.setString(5, item.getForWhom());
                return preparedStatement;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                item.setLessonId(keyHolder.getKey().longValue());
            }
        } else {
            jdbcTemplate.update(SQL_UPDATE,
                    item.getAuthor().getUserId(),
                    item.getCreatedAt(),
                    item.getContent(),
                    item.getSubject(),
                    item.getForWhom(),
                    item.getLessonId()
            );
        }
        return item;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.queryForObject(SQL_DELETE_BY_ID, rowMapper, id);
    }
}
