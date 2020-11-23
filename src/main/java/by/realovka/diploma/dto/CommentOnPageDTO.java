package by.realovka.diploma.dto;

import by.realovka.diploma.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CommentOnPageDTO {

    private String text;
    private String dataTime;
    private Post post;
    private String userName;
}
