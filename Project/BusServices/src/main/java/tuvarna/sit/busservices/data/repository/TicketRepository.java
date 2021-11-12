package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import tuvarna.sit.busservices.data.entities.Ticket;

import java.util.List;
import java.util.Optional;

public class TicketRepository implements DAORepository<Ticket>{

    private static final Logger log = Logger.getLogger(TicketRepository.class);

    public static TicketRepository getInstance() {
        return TicketRepository.TicketRepositoryHolder.INSTANCE;
    }

    private static class TicketRepositoryHolder {

        public static final TicketRepository INSTANCE = new TicketRepository();
    }

    @Override
    public void save(Ticket object) {

    }

    @Override
    public void update(Ticket object) {

    }

    @Override
    public void delete(Ticket object) {

    }

    @Override
    public Optional<Ticket> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Ticket> getAll() {
        return null;
    }


}
