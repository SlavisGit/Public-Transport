package tuvarna.sit.busservices.data.entities;

import javax.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "[USER]")
@Entity
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "[password]", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "admintId", nullable = true, referencedColumnName = "id")
    private Administrator administrator;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cashierId", nullable = true, referencedColumnName = "id")
    private Cashier cashier;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stationId", nullable = true, referencedColumnName = "id")
    private Station station;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "companyId", nullable = true, referencedColumnName = "id")
    private Company company;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userTypeId", nullable = false, referencedColumnName = "id")
    private UserType userType;

    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public User() {
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Administrator getAdmin() {
        return administrator;
    }

    public void setAdmin(Administrator admin) {
        this.administrator = admin;
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
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
