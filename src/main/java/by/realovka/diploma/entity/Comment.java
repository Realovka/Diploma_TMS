package by.realovka.diploma.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Cacheable(false)
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    private LocalDateTime localDateTime;
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Post.class)
    @LazyCollection(LazyCollectionOption.TRUE)
    private Post post;
    @JoinColumn(name="user_name", referencedColumnName = "username")
    @OneToOne(targetEntity = User.class)
    @LazyCollection(LazyCollectionOption.TRUE)
    private User user;

    public Comment(String text, LocalDateTime localDateTime,Post post, User user) {
        this.text = text;
        this.localDateTime = localDateTime;
        this.post = post;
        this.user = user;
    }
}
