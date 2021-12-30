package tuvarna.sit.busservices.business.services;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Administrator;
import tuvarna.sit.busservices.data.entities.OrderTickets;
import tuvarna.sit.busservices.data.repository.OrderTicketsRepository;
import tuvarna.sit.busservices.presentation.models.AdministratorListView;
import tuvarna.sit.busservices.presentation.models.OrderTicketsView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderTicketsService implements Service<OrderTickets>{
    private final OrderTicketsRepository orderTicketsRepository = OrderTicketsRepository.getInstance();
    public static OrderTicketsService getInstance() {
        return OrderTicketsService.OrderTicketsServiceHolder.INSTANCE;
    }

    private static class OrderTicketsServiceHolder {
        public static final OrderTicketsService INSTANCE = new OrderTicketsService();
    }

    public void save(OrderTickets orderTickets) {
        orderTicketsRepository.save(orderTickets);
    }

    public void delete(OrderTickets orderTickets) {
        orderTicketsRepository.delete(orderTickets);
    }

    @Override
    public OrderTickets getById(Long id) {
        return orderTicketsRepository.getById(id);
    }

    public ObservableList<OrderTickets> getAll() {
        List<OrderTickets> users = orderTicketsRepository.getAll();
        return FXCollections.observableList(
                new ArrayList<>(users));
    }

    public ObservableList<OrderTickets> getAllFromStation(Long id) {
        List<OrderTickets> users = orderTicketsRepository.getAllFromStation(id);
        return FXCollections.observableList(
                new ArrayList<>(users));
    }

    public ObservableList<OrderTickets> getAllFromCompany(Long id) {
        List<OrderTickets> users = orderTicketsRepository.getAll();
        return FXCollections.observableList(
                new ArrayList<>(users));
    }

    public void update(OrderTickets orderTickets) {
        orderTicketsRepository.update(orderTickets);
    }
}
