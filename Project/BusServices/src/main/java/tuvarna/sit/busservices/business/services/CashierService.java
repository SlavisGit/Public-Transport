package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Cashier;
import tuvarna.sit.busservices.data.repository.CashierRepository;
import tuvarna.sit.busservices.presentation.models.CashierListView;

import java.util.List;
import java.util.stream.Collectors;

public class CashierService {
    private final CashierRepository cashierRepository = CashierRepository.getInstance();
    public static CashierService getInstance() {
        return CashierService.CashierServiceHolder.INSTANCE;
    }

    private static class CashierServiceHolder {
        public static final CashierService INSTANCE = new CashierService();
    }

    public ObservableList<CashierListView> getAll(){
        List<Cashier> stations = cashierRepository.getAll();
        return FXCollections.observableList(
                stations.stream().map(m -> new CashierListView(m.getFirstName(), m.getLastName(), m.getUcn(), m.getHonorarium(), m.getStation()))
                        .collect(Collectors.toList()));
    }
}
