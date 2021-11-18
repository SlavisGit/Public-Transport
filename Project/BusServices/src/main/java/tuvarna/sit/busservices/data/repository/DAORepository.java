package tuvarna.sit.busservices.data.repository;

import java.util.List;
import java.util.Optional;

public interface DAORepository<T> {
    void save(T object);
    void update(T object);
    void delete(T object);
    Optional<T> getById(Long id);
    List<T> getAll();
}
