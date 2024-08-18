package daos;

import models.Car;

import java.util.List;

public interface DAO<T> {
    T getByID(Long id);
    List<T> getAll();
    boolean create(T DTO);
    boolean update(Long id);
    boolean delete(Long id);
}
