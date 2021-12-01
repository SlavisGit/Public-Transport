package tuvarna.sit.busservices.data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name = "TRANSPORT")
@Entity
public class Transport implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @Column(name = "transportType", nullable = false)
    private String transportType;

    @OneToMany(mappedBy = "transportType")
    private Set<Travel> travelSet;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public Set<Travel> getTravelSet() {
        return travelSet;
    }

    public void setTravelSet(Set<Travel> travelSet) {
        this.travelSet = travelSet;
    }

    @Override
    public String toString() {
        return transportType ;
    }
}
