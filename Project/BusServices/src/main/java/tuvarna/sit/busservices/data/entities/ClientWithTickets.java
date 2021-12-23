package tuvarna.sit.busservices.data.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Table(name = "CLIENT_WITH_TICKETS")
@Entity
public class ClientWithTickets implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;

    @OneToOne
    @JoinColumn(name = "ticketId", nullable = false)
    private Ticket ticket;

    public ClientWithTickets(Client client, Ticket ticket) {
        this.client = client;
        this.ticket = ticket;
    }

    public ClientWithTickets() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "ClientWithTickets{" +
                "ID=" + ID +
                ", client=" + client +
                ", ticket=" + ticket +
                '}';
    }
}
