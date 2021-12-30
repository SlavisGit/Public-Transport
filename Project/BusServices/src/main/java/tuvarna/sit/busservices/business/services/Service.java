package tuvarna.sit.busservices.business.services;


import java.util.List;

public interface Service<T>{
    void save(T object);
    void update(T object);
    void delete(T object);
    T getById(Long id);
    List<T> getAll();
}
