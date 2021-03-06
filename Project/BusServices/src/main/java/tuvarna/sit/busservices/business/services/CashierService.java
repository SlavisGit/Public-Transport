package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Cashier;
import tuvarna.sit.busservices.data.repository.CashierRepository;
import tuvarna.sit.busservices.presentation.models.CashierListView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CashierService implements Service<Cashier>{
    private final CashierRepository cashierRepository = CashierRepository.getInstance();
    public static CashierService getInstance() {
        return CashierService.CashierServiceHolder.INSTANCE;
    }

    private static class CashierServiceHolder {
        public static final CashierService INSTANCE = new CashierService();
    }

    @Override
    public void save(Cashier object) {
        cashierRepository.save(object);
    }

    @Override
    public void update(Cashier object) {
        cashierRepository.update(object);
    }

    @Override
    public void delete(Cashier object) {
        cashierRepository.delete(object);
    }

    @Override
    public Cashier getById(Long id) {
        return cashierRepository.getById(id);
    }

    @Override
    public ObservableList<Cashier> getAll(){
        List<Cashier> stations = cashierRepository.getAll();
        return FXCollections.observableList(
                new ArrayList<>(stations));
    }
}
