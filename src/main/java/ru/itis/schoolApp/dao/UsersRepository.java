package ru.itis.schoolApp.dao;

import ru.itis.schoolApp.dao.base.CrudRepository;
import ru.itis.schoolApp.model.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByToken(String token);
    void updateAvatarForUser(Long userId, Long fileId);
    Optional<String> getTokenByUserId(Long userId);
    void createTokenForUser(Long userId, String token);
    void updateTokenForUser(Long userId, String token);
    void updateProfile(Long userId, User Item);
    List<User> findAllByRole(String role);
}
