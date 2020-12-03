package by.realovka.diploma.dto;

import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeOnPageDTO {

    private Post post;
    private User user;
}
