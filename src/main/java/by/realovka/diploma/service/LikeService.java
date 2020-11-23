package by.realovka.diploma.service;

import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;

public interface LikeService {
    void addLike(Post post, User user);

}
