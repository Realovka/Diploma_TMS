package by.realovka.diploma.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Cacheable(false)
@Table(name = "COMMENTS")
public class Comment implements Comparable<Comment>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    private LocalDateTime localDateTime;
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Post.class)
    private Post post;
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @OneToOne(targetEntity = User.class)
    private User user;

    public Comment(String text, LocalDateTime localDateTime,Post post, User user) {
        this.text = text;
        this.localDateTime = localDateTime;
        this.post = post;
        this.user = user;
    }

    @Override
    public int compareTo(Comment o) {
        return o.getLocalDateTime().compareTo(getLocalDateTime());
    }

}
