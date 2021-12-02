package ru.itis.schoolApp.dao.base;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, K> {
    Optional<T> findById(K id);
    List<T> findAll();
    T saveUpdate(T item);
    void delete(K id);
}
