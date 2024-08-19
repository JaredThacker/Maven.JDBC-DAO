package daos;

import models.Car;

import java.util.List;

public interface DAO {
    Car getByID(Long id);
    List<Car> getAll();
    boolean create(Car DTO);
    boolean update(Long id);
    boolean delete(Long id);
}
