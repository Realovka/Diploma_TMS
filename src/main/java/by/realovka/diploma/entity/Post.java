package by.realovka.diploma.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "POSTS")
public class Post implements Comparable<Post> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private String text;
    private LocalDateTime localDateTime;
    private long view;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "post_id",referencedColumnName = "id")
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "post_id",referencedColumnName = "id")
    private List<Like> likes = new ArrayList<>();
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(targetEntity = User.class)
    private User user;

    public Post(String text, LocalDateTime localDateTime, User user) {
        this.text = text;
        this.localDateTime = localDateTime;
        this.user = user;
    }

    @Override
    public int compareTo(Post o) {
        return o.getLocalDateTime().compareTo(getLocalDateTime());
    }
}
