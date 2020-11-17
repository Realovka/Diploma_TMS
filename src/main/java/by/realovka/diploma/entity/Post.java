package by.realovka.diploma.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "POSTS")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    private Timestamp timestamp;
    private long view;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "post_id",referencedColumnName = "id")
    private List<Comment> comments = new ArrayList<>();
    @Column(name = "user_id")
    private long userId;

    public Post(String text, Timestamp timestamp, long userId) {
        this.text = text;
        this.timestamp = timestamp;
        this.userId = userId;
    }

}
