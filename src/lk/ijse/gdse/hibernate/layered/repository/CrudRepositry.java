package lk.ijse.gdse.hibernate.layered.repository;

import java.util.List;

public interface CrudRepositry<T, ID> extends SuperRepositry{
    ID save(T object);

    boolean update(T object);

    T get(ID id);

    boolean delete(T object);

    List<T> getAll();

}
