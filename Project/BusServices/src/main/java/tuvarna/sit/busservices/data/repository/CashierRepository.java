package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import tuvarna.sit.busservices.data.entities.Cashier;

import java.util.List;
import java.util.Optional;

public class CashierRepository implements DAORepository<Cashier>{
    private static final Logger log = Logger.getLogger(CashierRepository.class);

    public static CashierRepository getInstance() {
        return CashierRepository.CashierRepositoryHolder.INSTANCE;
    }

    private static class CashierRepositoryHolder {
        public static final CashierRepository INSTANCE = new CashierRepository();
    }


    @Override
    public void save(Cashier object) {

    }

    @Override
    public void update(Cashier object) {

    }

    @Override
    public void delete(Cashier object) {

    }

    @Override
    public Optional<Cashier> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Cashier> getAll() {
        return null;
    }


}
