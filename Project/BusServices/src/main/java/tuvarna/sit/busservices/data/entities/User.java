package tuvarna.sit.busservices.data.entities;

import javax.persistence.*;

import java.io.Serializable;

@Table(name = "USER_ADMINISTRATOR")
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @Column(name = "username", nullable = false)
    private String firstName;

    @Column(name = "[password]", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "admintId", nullable = false)
    private Administrator admin;

    @ManyToOne
    @JoinColumn(name = "cashierId", nullable = false)
    private Cashier cashier;

    @ManyToOne
    @JoinColumn(name = "stationId", nullable = false)
    private Station station;

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "userTypeId", nullable = false)
    private UserType userType;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", cashier=" + cashier +
                ", station=" + station +
                ", company=" + company +
                ", userType=" + userType +
                '}';
    }
}
