package by.realovka.diploma.service;

import by.realovka.diploma.dto.PostAddDTO;
import by.realovka.diploma.dto.PostUpdateDTO;
import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.repository.PostRepository;
import by.realovka.diploma.repository.UserRepository;
import by.realovka.diploma.service.exseption.NoSuchCommentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void addPost(User user, PostAddDTO postAddDTO) {
        Post post = new Post(postAddDTO.getTextPostAddDTO(), new java.sql.Timestamp(System.currentTimeMillis()), user.getId());
        postRepository.save(post);
    }

    public List<Post> findAllPosts(User user) {
        return postRepository.findAllByUserId(user.getId());
    }

    public Optional<Post> findPost(long id) {
        return postRepository.findById(id);
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    public void updatePost(long id, PostUpdateDTO postUpdateDTO) {
        Optional<Post> updatePost = postRepository.findById(id);
        if (updatePost.isPresent()) {
            Post post = updatePost.get();
            post.setText(postUpdateDTO.getTextPostUpdateDTO());
            postRepository.save(post);
        }
    }


}
