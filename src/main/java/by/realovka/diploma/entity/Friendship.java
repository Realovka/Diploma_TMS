package by.realovka.diploma.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "FRIENDSHIP")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JoinColumn(name = "user_auth", referencedColumnName = "id")
    @OneToOne(targetEntity = User.class)
    private User auth;
    @JoinColumn(name = "user_person", referencedColumnName = "id")
    @OneToOne(targetEntity = User.class)
    private User person;

    public Friendship(User auth, User person) {
        this.auth = auth;
        this.person = person;
    }
}
