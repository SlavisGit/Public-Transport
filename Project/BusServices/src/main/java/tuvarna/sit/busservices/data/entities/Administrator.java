package tuvarna.sit.busservices.data.entities;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Table(name = "ADMINISTRATOR")
@Entity
public class Administrator implements Serializable{

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

    @OneToOne(mappedBy = "administrator", fetch = FetchType.EAGER)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "administrator", fetch = FetchType.EAGER)
    private Set<Company> companySet = new HashSet<>();

    @OneToMany(mappedBy = "admin",  fetch = FetchType.EAGER)
    private Set<Station> stationSet = new HashSet<>();

    public Administrator(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Administrator() {
    }

    public Set<Company> getCompanySet() {
        return companySet;
    }

    public void setCompanySet(Set<Company> companySet) {
        this.companySet = companySet;
    }

    public Set<Station> getStationSet() {
        return stationSet;
    }

    public void setStationSet(Set<Station> stationSet) {
        this.stationSet = stationSet;
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

    @Override
    public String toString() {
        return  firstName + ' ' +
                 lastName ;
    }
}
