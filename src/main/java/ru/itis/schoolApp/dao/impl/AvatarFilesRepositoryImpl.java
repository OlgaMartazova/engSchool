package ru.itis.schoolApp.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.schoolApp.dao.AvatarFilesRepository;
import ru.itis.schoolApp.model.AvatarFile;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class AvatarFilesRepositoryImpl implements AvatarFilesRepository {

    private final static String SQL_INSERT = "insert into avatar_file(original_file_name, storage_file_name, size, type) " +
            "values (?, ?, ?, ?)";
    private final static String SQL_UPDATE = "update avatar_file set original_file_name = ?, storage_file_name = ?, size = ?, type = ? where avatar_file_id = ?";
    private final static String SQL_SELECT_BY_ID = "select * from avatar_file where avatar_file_id = ?";
    private final static String SQL_SELECT_ALL = "select * from avatar_file";
    private final static String SQL_DELETE_BY_ID = "delete from avatar_file where avatar_file_id = ?";

    private final RowMapper<AvatarFile> rowMapper = (row, rowNumber) ->
            AvatarFile.builder()
                    .fileId(row.getLong("avatar_file_id"))
                    .originalFileName(row.getString("original_file_name"))
                    .storageFileName(row.getString("storage_file_name"))
                    .size(row.getLong("size"))
                    .type(row.getString("type"))
                    .build();

    private final JdbcTemplate jdbcTemplate;

    public AvatarFilesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Optional<AvatarFile> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<AvatarFile> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @Override
    public AvatarFile saveUpdate(AvatarFile item) {
        if(item.getFileId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, new String[]{"avatar_file_id"});
                preparedStatement.setString(1, item.getOriginalFileName());
                preparedStatement.setString(2, item.getStorageFileName());
                preparedStatement.setLong(3, item.getSize());
                preparedStatement.setString(4, item.getType());
                return preparedStatement;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                item.setFileId(keyHolder.getKey().longValue());
            }
        } else {
            jdbcTemplate.update(SQL_UPDATE,
                    item.getOriginalFileName(),
                    item.getStorageFileName(),
                    item.getSize(),
                    item.getType(),
                    item.getFileId()
            );
        }
        return item;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.queryForObject(SQL_DELETE_BY_ID, rowMapper, id);
    }
}
