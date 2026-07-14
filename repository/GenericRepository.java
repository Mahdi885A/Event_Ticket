package repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository <T> {
    Long save(T t);

    boolean update (Long id,T t);

    boolean delete (Long id);

    Optional<T> findById(Long id);

    boolean cancel(Long id);

    List<T> findAll();


}
