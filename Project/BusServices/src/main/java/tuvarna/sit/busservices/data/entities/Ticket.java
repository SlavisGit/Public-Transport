package tuvarna.sit.busservices.data.entities;
import javax.persistence.*;
import java.io.Serializable;

@Table(name = "DESTINATION")
@Entity
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "travelId", nullable = false)
    private Travel travel;

    @ManyToOne
    @JoinColumn(name = "cashierId", nullable = false)
    private Cashier cashier;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "statusTypeId", nullable = false)
    private Status status;

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
        return "Ticket{" +
                "ID=" + ID +
                ", travel=" + travel +
                ", cashier=" + cashier +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
