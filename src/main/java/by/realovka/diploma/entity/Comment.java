package by.realovka.diploma.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    private Timestamp timestamp;
    @Column(name = "post_id")
    private long postId;
    private String userWhoAddCommentName;
    private String userWhoWritePostName;

    public Comment(String text, Timestamp timestamp, long postId, String userWhoAddCommentName, String userWhoWritePostName) {
        this.text = text;
        this.timestamp = timestamp;
        this.postId = postId;
        this.userWhoAddCommentName = userWhoAddCommentName;
        this.userWhoWritePostName = userWhoWritePostName;
    }

}
