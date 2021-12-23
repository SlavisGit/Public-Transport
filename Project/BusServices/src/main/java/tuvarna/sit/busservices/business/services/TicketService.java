package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Ticket;
import tuvarna.sit.busservices.data.repository.TicketRepository;
import tuvarna.sit.busservices.presentation.models.TicketListView;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {
    private final TicketRepository ticketRepository = TicketRepository.getInstance();
    public static TicketService getInstance() {
        return TicketService.TicketServiceHolder.INSTANCE;
    }

    private static class TicketServiceHolder {
        public static final TicketService INSTANCE = new TicketService();
    }

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public ObservableList<TicketListView> getAll(){
        List<Ticket> stations = ticketRepository.getAll();
        return FXCollections.observableList(
                stations.stream().map(m -> new TicketListView(m.getTravel(), m.getCashier(), m.getPrice(), m.getStatus(),m.getClientWithTickets().getClient()))
                        .collect(Collectors.toList()));
    }
    public ObservableList<TicketListView> getFromTravel(Long id){
        List<Ticket> stations = ticketRepository.getWhereTravel(id);
        return FXCollections.observableList(
                stations.stream().map(m -> new TicketListView(m.getTravel(), m.getCashier(), m.getPrice(), m.getStatus(),m.getClientWithTickets().getClient()))
                        .collect(Collectors.toList()));
    }
}
