package daos;

import models.Car;

import java.util.List;

public interface DAO<T> {
    T getByID(Long id);
    List<T> getAll();
    T getCarByVIN(Long vin);
    boolean create(T DTO);
    boolean update(Long id);
    boolean delete(Long id);
}
