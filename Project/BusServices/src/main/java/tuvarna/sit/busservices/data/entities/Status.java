package tuvarna.sit.busservices.data.entities;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Table(name = "STATUS")
@Entity
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @Column(name = "[status]", nullable = false)
    private String status;

    @OneToMany(mappedBy = "status")
    private Set<Ticket> ticketSet;

    public Set<Ticket> getTicketSet() {
        return ticketSet;
    }

    public void setTicketSet(Set<Ticket> ticketSet) {
        this.ticketSet = ticketSet;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status{" +
                "ID=" + ID +
                ", status='" + status + '\'' +
                ", ticketSet=" + ticketSet +
                '}';
    }
}