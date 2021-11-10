package tuvarna.sit.busservices.data.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Table(name = "TRAVEL_TYPE")
@Entity
public class TravelType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @Column(name = "travelType", nullable = false)
    private String travelType;

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
        return "TravelType{" +
                "ID=" + ID +
                ", travelType='" + travelType + '\'' +
                '}';
    }
}
