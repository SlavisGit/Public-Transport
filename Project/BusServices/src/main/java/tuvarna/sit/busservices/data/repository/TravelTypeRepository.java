package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import tuvarna.sit.busservices.data.entities.TravelType;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.UserType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TravelTypeRepository implements DAORepository<TravelType>{

    private static final Logger log = Logger.getLogger(TravelTypeRepository.class);

    public static TravelTypeRepository getInstance() {
        return TravelTypeRepository.TravelTypeRepositoryHolder.INSTANCE;
    }

    public static class TravelTypeRepositoryHolder {

        public static final TravelTypeRepository INSTANCE = new TravelTypeRepository();
    }

    @Override
    public void save(TravelType object) {

    }

    @Override
    public void update(TravelType object) {

    }

    @Override
    public void delete(TravelType object) {

    }

    @Override
    public Optional<TravelType> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<TravelType> getAll() {
        return null;
    }


}
