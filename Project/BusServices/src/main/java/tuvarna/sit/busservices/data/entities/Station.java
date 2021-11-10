package tuvarna.sit.busservices.data.entities;
import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Table(name = "STATION")
@Entity
public class Station implements Serializable {
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
    private LocalDate workTimeStart;

    @Column(name = "workTimeEnd", nullable = false)
    private LocalDate workTimeEnd;

    @ManyToOne
    @JoinColumn(name = "adminId", nullable = false)
    private Administrator admin;

    @OneToMany(mappedBy = "station")
    private Set<User> userSetl;

    @OneToMany(mappedBy = "station")
    private Set<Travel> travelSet;

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

    public Set<Travel> getTravelSet() {
        return travelSet;
    }

    public void setTravelSet(Set<Travel> travelSet) {
        this.travelSet = travelSet;
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

    public LocalDate getWorkTimeStart() {
        return workTimeStart;
    }

    public void setWorkTimeStart(LocalDate workTimeStart) {
        this.workTimeStart = workTimeStart;
    }

    public LocalDate getWorkTimeEnd() {
        return workTimeEnd;
    }

    public void setWorkTimeEnd(LocalDate workTimeEnd) {
        this.workTimeEnd = workTimeEnd;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Station{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", workTimeStart=" + workTimeStart +
                ", workTimeEnd=" + workTimeEnd +
                ", admin=" + admin +
                ", userSetl=" + userSetl +
                ", travelSet=" + travelSet +
                ", cashierSet=" + cashierSet +
                '}';
    }
}
