package repository;

import java.util.List;

public interface GenericRepository <T> {
    T save(T t);

    boolean update (Long id);

    boolean delete (Long id);

    T findById(Long id);

    List<T> findAll();

}
