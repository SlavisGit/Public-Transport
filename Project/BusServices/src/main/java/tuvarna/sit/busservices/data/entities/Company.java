package tuvarna.sit.busservices.data.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name = "COMPANY")
@Entity
public class Company implements Serializable {
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

    @ManyToOne
    @JoinColumn(name = "adminId", nullable = false)
    private Administrator administrator;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private Set<Travel> travelSet = new HashSet<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private Set<OrderTickets> orderTickets = new HashSet<>();

    public Set<OrderTickets> getOrderTickets() {
        return orderTickets;
    }

    public void setOrderTickets(Set<OrderTickets> orderTickets) {
        this.orderTickets = orderTickets;
    }

    @OneToOne(mappedBy = "company", fetch = FetchType.EAGER)
    private User user;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Set<Travel> getTravelSet() {
        return travelSet;
    }

    public void setTravelSet(Set<Travel> travelSet) {
        this.travelSet = travelSet;
    }

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

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Company{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
