package by.realovka.diploma.dto;

import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.Like;
import by.realovka.diploma.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOnPageDTO {
    private long id;
    private String text;
    private String dataTime;
    private List<Comment> comments = new ArrayList<>();
    private List<Like> likes = new ArrayList<>();
    private User user;

    public PostOnPageDTO(long id, String text, String dataTime, List<Comment> comments,  User user) {
        this.id = id;
        this.text = text;
        this.dataTime = dataTime;
        this.comments = comments;
        this.user = user;
    }
}
