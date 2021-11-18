package tuvarna.sit.busservices.data.entities;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Table(name = "USER_TYPE")
@Entity
public class UserType implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @Column(name = "userType", nullable = false)
    private String userType;

    @OneToMany(mappedBy = "userType", fetch = FetchType.EAGER)
    private Set<User> userSet = new HashSet<>();

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Set<User> getUserSet() {
        return userSet = new HashSet<>();
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;

    }

    @Override
    public String toString() {
        return "UserType{" +
                "ID=" + ID +
                ", userType='" + userType + '\'' +
                ", userSet=" + userSet +
                '}';
    }
}
