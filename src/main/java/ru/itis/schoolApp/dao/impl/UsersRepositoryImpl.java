package ru.itis.schoolApp.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.schoolApp.dao.UsersRepository;
import ru.itis.schoolApp.model.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {

    private final static String SQL_INSERT = "insert into users(first_name, last_name, email, password_hash, role, avatar_id) " +
            "values (?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "update users set first_name = ?, last_name = ?, email = ?, password_hash = ?, role = ?, avatar_id = ? where user_id = ?";
    private final static String SQL_UPDATE_AVATAR = "update users set avatar_id = ? where user_id = ?";
    private final static String SQL_UPDATE_TOKEN = "update user_token set token = ? where user_token_id = ?";
    private final static String SQL_CREATE_TOKEN = "insert into user_token(user_token_id, token) VALUES (?, ?)";
    private final static String SQL_SELECT_BY_ID = "select * from users where user_id = ?";
    private final static String SQL_SELECT_TOKEN_BY_USER_ID = "select * from user_token where user_token_id = ?";
    private final static String SQL_SELECT_BY_EMAIL = "select * from users where email = ?";
    private final static String SQL_SELECT_BY_TOKEN = "select * from user_token ut join users u on ut.user_token_id = u.user_id where token = ?";
    private final static String SQL_SELECT_ALL = "select * from users";
    private final static String SQL_DELETE_BY_ID = "delete from users where user_id = ?";
    private final static String SQL_UPDATE_PROFILE = "update users set first_name = ?, last_name = ?, role = ? where user_id = ?";
    private final static String SQL_SELECT_BY_ROLE = "select * from users where role = ? order by user_id";

    private final RowMapper<User> rowMapper = (row, rowNumber) ->
            User.builder()
                    .userId(row.getLong("user_id"))
                    .firstName(row.getString("first_name"))
                    .lastName(row.getString("last_name"))
                    .email(row.getString("email"))
                    .hashPassword(row.getInt("password_hash"))
                    .role(row.getString("role"))
                    .avatarId(row.getLong("avatar_id") == 0 ? null : row.getLong("avatar_id"))
                    .build();

    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, rowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByToken(String token) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_TOKEN, rowMapper, token));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateAvatarForUser(Long userId, Long fileId) {
        jdbcTemplate.update(SQL_UPDATE_AVATAR, fileId, userId);
    }

    @Override
    public Optional<String> getTokenByUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_TOKEN_BY_USER_ID, resultSet -> {
            if (resultSet.next()) {
                return Optional.of(resultSet.getString("token"));
            } else {
                return Optional.empty();
            }
        }, userId);
    }

    @Override
    public void createTokenForUser(Long userId, String token) {
        jdbcTemplate.update(SQL_CREATE_TOKEN, userId, token);
    }

    @Override
    public void updateTokenForUser(Long userId, String token) {
        jdbcTemplate.update(SQL_UPDATE_TOKEN, token, userId);
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @Override
    public List<User> findAllByRole(String role) {
        return jdbcTemplate.query(SQL_SELECT_BY_ROLE, rowMapper, role);
    }

    @Override
    public User saveUpdate(User item) {
        if(item.getUserId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, new String[]{"user_id"});
                preparedStatement.setString(1, item.getFirstName());
                preparedStatement.setString(2, item.getLastName());
                preparedStatement.setString(3, item.getEmail());
                preparedStatement.setInt(4, item.getHashPassword());
                preparedStatement.setString(5, item.getRole());
                if(item.getAvatarId() != null) {
                    preparedStatement.setLong(6, item.getAvatarId());
                } else {
                    preparedStatement.setNull(6, Types.NULL);
                }
                return preparedStatement;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                item.setUserId(keyHolder.getKey().longValue());
            }
        } else {
            jdbcTemplate.update(SQL_UPDATE,
                    item.getFirstName(),
                    item.getLastName(),
                    item.getEmail(),
                    item.getHashPassword(),
                    item.getRole(),
                    item.getAvatarId(),
                    item.getUserId()
            );
        }
        return item;
    }

    @Override
    public void updateProfile(Long userId, User item) {
        jdbcTemplate.update(SQL_UPDATE_PROFILE,
                item.getFirstName(),
                item.getLastName(),
                item.getRole(),
                userId
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.queryForObject(SQL_DELETE_BY_ID, rowMapper, id);
    }
}
