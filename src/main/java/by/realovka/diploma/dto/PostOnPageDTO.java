package by.realovka.diploma.dto;

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
    private long view;
    private List<CommentOnPageDTO> comments = new ArrayList<>();
    private List<LikeOnPageDTO> likes = new ArrayList<>();
    private User user;

    public PostOnPageDTO(long id, String text, String dataTime, long view, User user) {
        this.id = id;
        this.text = text;
        this.dataTime = dataTime;
        this.view = view;
        this.user = user;
    }

}
