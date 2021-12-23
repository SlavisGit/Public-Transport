package tuvarna.sit.busservices.data.entities;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name = "TICKET")
@Entity
public class Ticket implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "travelId", nullable = false)
    private Travel travel;

    @ManyToOne
    @JoinColumn(name = "cashierId", nullable = true)
    private Cashier cashier;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "statusTypeId", nullable = false)
    private Status status;

    @OneToOne(mappedBy = "ticket")
    private ClientWithTickets clientWithTickets;

    public ClientWithTickets getClientWithTickets() {
        return clientWithTickets;
    }

    public void setClientWithTickets(ClientWithTickets clientWithTickets) {
        this.clientWithTickets = clientWithTickets;
    }

    public Ticket(Travel travel, Double price, Status status) {
        this.travel = travel;
        this.price = price;
        this.status = status;
    }

    public Ticket() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID=" + ID +
                ", price=" + price ;
    }
}
