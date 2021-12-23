package tuvarna.sit.busservices.data.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Table(name = "TRAVEL_TYPE")
@Entity
public class TravelType implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @Column(name = "travelType", nullable = false)
    private String travelType;

    public TravelType() {
    }

    public TravelType(String travelType) {
        this.travelType = travelType;
    }

    public void setTravelSet(Set<Travel> travelSet) {
        this.travelSet = travelSet;
    }

    public Set<Travel> getTravelSet() {
        return travelSet;
    }

    @OneToMany(mappedBy = "travelType")
    private Set<Travel> travelSet;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    @Override
    public String toString() {
        return travelType;
    }
}
