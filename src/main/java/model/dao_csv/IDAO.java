package model.dao_csv;

import model.entities.Biome;

import java.util.List;

public interface  IDAO<T> {
    int create(T entity);
    boolean update(T entity);
    boolean deleteById(Integer id);
    boolean deleteByObject(T entity);
    T findById(Integer id);
    List<T> findAll();
}