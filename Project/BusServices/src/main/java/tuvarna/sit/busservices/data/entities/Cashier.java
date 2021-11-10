package tuvarna.sit.busservices.data.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name = "CASHIER")
@Entity
public class Cashier implements Serializable {
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

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Set<User> getUserSetl() {
        return userSetl;
    }

    public void setUserSetl(Set<User> userSetl) {
        this.userSetl = userSetl;
    }

    @ManyToOne
    @JoinColumn(name = "stationId", nullable = false)
    private Station station;

    @OneToMany(mappedBy = "cashier")
    private Set<Ticket> ticketSet;

    @OneToMany(mappedBy = "cashier")
    private Set<User> userSetl;

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


    @Override
    public String toString() {
        return "Cashier{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ucn='" + ucn + '\'' +
                ", honorarium=" + honorarium +
                ", station=" + station +
                ", ticketSet=" + ticketSet +
                ", userSetl=" + userSetl +
                '}';
    }
}
