package by.realovka.diploma.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "LIKES")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Post.class)
    @LazyCollection(LazyCollectionOption.TRUE)
    private Post post;
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @OneToOne(targetEntity = User.class)
    @LazyCollection(LazyCollectionOption.TRUE)
    private User user;

    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
    }
}
