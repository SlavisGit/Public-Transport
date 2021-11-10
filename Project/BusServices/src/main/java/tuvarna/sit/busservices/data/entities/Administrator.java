package tuvarna.sit.busservices.data.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Table(name = "ADMINISTRATOR")
@Entity
public class Administrator implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "admin")
    private Set<User> usersSet;

    @OneToMany(mappedBy = "administrator")
    private Set<Company> companySet;

    @OneToMany(mappedBy = "admin")
    private Set<Station> stationSet;

    public Set<User> getUsersSet() {
        return usersSet;
    }

    public void setUsersSet(Set<User> usersSet) {
        this.usersSet = usersSet;
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
        return "Administrator{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userAdminSet=" + usersSet +
                ", companySet=" + companySet +
                ", stationSet=" + stationSet +
                '}';
    }
}
