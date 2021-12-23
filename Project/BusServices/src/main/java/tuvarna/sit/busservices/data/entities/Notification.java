package tuvarna.sit.busservices.data.entities;

import javax.persistence.*;
import javax.xml.namespace.QName;
import java.io.Serial;
import java.io.Serializable;

@Table(name = "NOTIFICATIONS")
@Entity
public class Notification implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @Column(name = "coment", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Override
    public String toString() {
        return "Notification{" +
                "comment='" + comment + '\'' +
                ", user=" + user +
                '}';
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
