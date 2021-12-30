package tuvarna.sit.busservices.data.entities;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Table(name = "CASHIER")
@Entity
public class Cashier implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "ucn", nullable = false)
    private String ucn;

    @Column(name = "honorarium", nullable = false)
    private Double honorarium;

    @Column(name = "countTicket", nullable = true)
    private int countTicket;

    public int getCountTicket() {
        return countTicket;
    }

    public void setCountTicket(int countTicket) {
        this.countTicket = countTicket;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @ManyToOne
    @JoinColumn(name = "stationId", nullable = false)
    private Station station;

    @OneToMany(mappedBy = "cashier", fetch = FetchType.EAGER)
    private Set<Ticket> ticketSet;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne(mappedBy = "cashier")
    private User user;

    public Set<Ticket> getTicketSet() {
        return ticketSet;
    }

    public void setTicketSet(Set<Ticket> ticketSet) {
        this.ticketSet = ticketSet;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUcn() {
        return ucn;
    }

    public void setUcn(String ucn) {
        this.ucn = ucn;
    }

    public Double getHonorarium() {
        return honorarium;
    }

    public void setHonorarium(Double honorarium) {
        this.honorarium = honorarium;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Cashier(String firstName, String lastName, String ucn, Double honorarium, Station station) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ucn = ucn;
        this.honorarium = honorarium;
        this.station = station;
    }

    public Cashier(String firstName, String lastName, String ucn, Double honorarium, int countTicket, Station station) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ucn = ucn;
        this.honorarium = honorarium;
        this.countTicket = countTicket;
        this.station = station;
    }

    public Cashier() {
    }

    @Override
    public String toString() {
        return  firstName + '\'' +
                 lastName + '\'';
    }
}
