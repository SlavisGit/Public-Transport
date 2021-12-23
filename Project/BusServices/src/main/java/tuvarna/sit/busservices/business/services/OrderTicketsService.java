package tuvarna.sit.busservices.business.services;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Administrator;
import tuvarna.sit.busservices.data.entities.OrderTickets;
import tuvarna.sit.busservices.data.repository.OrderTicketsRepository;
import tuvarna.sit.busservices.presentation.models.AdministratorListView;
import tuvarna.sit.busservices.presentation.models.OrderTicketsView;

import java.util.List;
import java.util.stream.Collectors;

public class OrderTicketsService {
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

    public ObservableList<OrderTicketsView> getAll() {
        List<OrderTickets> users = orderTicketsRepository.getAll();
        return FXCollections.observableList(
                users.stream()
                        .map(m -> new OrderTicketsView(m.getID(),m.getStation(),m.getTicket(), m.getCompany(), m.getStatus()))
                        .collect(Collectors.toList()));
    }
}
