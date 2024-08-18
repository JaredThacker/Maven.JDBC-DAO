package daos;

import java.util.Collections;
import java.util.List;

public class CarDAO implements DAO{
    @Override
    public Object getByID(Long id) {
        return null;
    }

    @Override
    public List getAll() {
        return Collections.emptyList();
    }

    @Override
    public boolean create(Object DTO) {
        return false;
    }

    @Override
    public boolean update(Long id) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
