package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import tuvarna.sit.busservices.data.entities.ClientWithTickets;
import tuvarna.sit.busservices.data.repository.ClientWithTicketsRepository;

import java.util.ArrayList;
import java.util.List;

public class ClientWithTicketsService implements Service<ClientWithTickets>{
    private final ClientWithTicketsRepository clientWithTicketsRepository = ClientWithTicketsRepository.getInstance();
    public static ClientWithTicketsService getInstance() {
        return ClientWithTicketsService.ClientWithTicketsServiceHolder.INSTANCE;
    }

    private static class ClientWithTicketsServiceHolder {
        public static final ClientWithTicketsService INSTANCE = new ClientWithTicketsService();
    }

    @Override
    public void save(ClientWithTickets object) {
        clientWithTicketsRepository.save(object);
    }

    @Override
    public void update(ClientWithTickets object) {
        clientWithTicketsRepository.update(object);
    }

    @Override
    public void delete(ClientWithTickets object) {
        clientWithTicketsRepository.delete(object);
    }

    @Override
    public ClientWithTickets getById(Long id) {
        return clientWithTicketsRepository.getById(id);
    }

    @Override
    public List<ClientWithTickets> getAll() {
        List<ClientWithTickets> clientWithTickets = clientWithTicketsRepository.getAll();
        return FXCollections.observableList(
                new ArrayList<>(clientWithTickets));
    }
}
