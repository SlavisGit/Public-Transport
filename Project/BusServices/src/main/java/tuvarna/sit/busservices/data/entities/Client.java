package tuvarna.sit.busservices.data.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name = "CLIENT")
@Entity
public class Client implements Serializable {

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

    @OneToMany(mappedBy = "client")
    private Set<ClientWithTickets> clientWithTicketsSet = new HashSet<>();

    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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

    public Set<ClientWithTickets> getClientWithTicketsSet() {
        return clientWithTicketsSet;
    }

    public void setClientWithTicketsSet(Set<ClientWithTickets> clientWithTicketsSet) {
        this.clientWithTicketsSet = clientWithTicketsSet;
    }

    @Override
    public String toString() {
        return firstName + '\'' +lastName + '\'';
    }
}
