package model.dao;

import java.util.List;
import java.util.UUID;

public interface IDAO<T, ID> {
    UUID create(T entity);
    boolean update(T entity);
    boolean deleteById(UUID id);
    boolean deleteByObject(T entity);
    T findById(UUID id);
    List<T> findAll();
}
