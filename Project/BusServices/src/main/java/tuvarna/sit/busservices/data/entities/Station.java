package tuvarna.sit.busservices.data.entities;
import javax.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table(name = "STATION")
@Entity
public class Station implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @Column(name = "[name]", nullable = false)
    private String name;

    @Column(name = "[address]", nullable = false)
    private String address;

    @Column(name = "workTimeStart", nullable = false)
    private Time workTimeStart;

    @Column(name = "workTimeEnd", nullable = false)
    private Time workTimeEnd;

    @ManyToOne
    @JoinColumn(name = "adminId", nullable = false)
    private Administrator admin;

    @OneToOne(mappedBy = "station", fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "station")
    private Set<Ticket> ticketSet = new HashSet<>();

    public Set<Ticket> getTicketSet() {
        return ticketSet;
    }

    public void setTicketSet(Set<Ticket> ticketSet) {
        this.ticketSet = ticketSet;
    }

    @OneToMany(mappedBy = "station")
    private Set<OrderTickets> orderTickets = new HashSet<>();

    public Set<OrderTickets> getOrderTickets() {
        return orderTickets;
    }

    public void setOrderTickets(Set<OrderTickets> orderTickets) {
        this.orderTickets = orderTickets;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Station(String name, String address, Time workTimeStart, Time workTimeEnd) {
        this.name = name;
        this.address = address;
        this.workTimeStart = workTimeStart;
        this.workTimeEnd = workTimeEnd;
    }

    public Station() {
    }

    public Set<Cashier> getCashierSet() {
        return cashierSet;
    }

    public void setCashierSet(Set<Cashier> cashierSet) {
        this.cashierSet = cashierSet;
    }

    @OneToMany(mappedBy = "station")
    private Set<Cashier> cashierSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Time getWorkTimeStart() {
        return workTimeStart;
    }

    public void setWorkTimeStart(Time workTimeStart) {
        this.workTimeStart = workTimeStart;
    }

    public Time getWorkTimeEnd() {
        return workTimeEnd;
    }

    public void setWorkTimeEnd(Time workTimeEnd) {
        this.workTimeEnd = workTimeEnd;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return name;
    }
}
