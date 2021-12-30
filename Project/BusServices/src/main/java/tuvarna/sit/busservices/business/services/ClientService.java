package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import tuvarna.sit.busservices.data.entities.Client;
import tuvarna.sit.busservices.data.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

public class ClientService implements Service<Client>{
    private final ClientRepository clientRepository = ClientRepository.getInstance();
    public static ClientService getInstance() {
        return ClientService.ClientServiceHolder.INSTANCE;
    }

    private static class ClientServiceHolder {
        public static final ClientService INSTANCE = new ClientService();
    }

    @Override
    public void save(Client object) {
        clientRepository.save(object);
    }

    @Override
    public void update(Client object) {
        clientRepository.update(object);
    }

    @Override
    public void delete(Client object) {
        clientRepository.delete(object);
    }

    @Override
    public Client getById(Long id) {
        return  clientRepository.getById(id);
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = clientRepository.getAll();
        return FXCollections.observableList(
                new ArrayList<>(clients));
    }
}
