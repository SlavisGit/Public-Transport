package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Destination;
import tuvarna.sit.busservices.data.entities.Ticket;
import tuvarna.sit.busservices.data.repository.TicketRepository;
import tuvarna.sit.busservices.presentation.models.TicketListView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketService implements Service<Ticket>{
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

    @Override
    public void update(Ticket object) {
        ticketRepository.update(object);
    }

    public ObservableList<Ticket> getAll(){
        List<Ticket> stations = ticketRepository.getAll();
        return FXCollections.observableList(
                new ArrayList<>(stations));
    }
    public ObservableList<Ticket> getFromTravel(Long id){
        List<Ticket> stations = ticketRepository.getWhereTravel(id);
        return FXCollections.observableList(
                new ArrayList<>(stations));
    }

    public ObservableList<Ticket> getWhereDestination(Destination destination){
        List<Ticket> stations = ticketRepository.getWhereDestination(destination);
        return FXCollections.observableList(
                new ArrayList<>(stations));
    }
    public void delete(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

    @Override
    public Ticket getById(Long id) {
        return ticketRepository.getById(id);
    }
}
