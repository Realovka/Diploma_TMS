package by.realovka.diploma.service;

import by.realovka.diploma.dto.PostAddDTO;
import by.realovka.diploma.dto.PostOnPageDTO;
import by.realovka.diploma.dto.PostUpdateDTO;
import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;

import java.util.List;

public interface PostService {
    void addPost(User user, PostAddDTO postAddDTO);
    void deletePost(long id);
    void updatePost(long id, PostUpdateDTO postUpdateDTO);
    Post getPostByComment(Comment comment);
    Post getPostById(long id);
    List<PostOnPageDTO> getPosts(long userId);
    List<Post> findAllPosts(long userId);
    long addViewToPost(long postId);
}
