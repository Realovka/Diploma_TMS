package by.realovka.diploma.service;

import by.realovka.diploma.dto.PostAddDTO;
import by.realovka.diploma.dto.PostOnPageDTO;
import by.realovka.diploma.dto.PostUpdateDTO;
import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.repository.PostRepository;
import by.realovka.diploma.service.exсeption.NoSuchPostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void addPost(User user, PostAddDTO postAddDTO) {
        Post post = new Post(postAddDTO.getTextPostAddDTO(), LocalDateTime.now(), user);
        postRepository.save(post);
    }

    @Override
    public List<Post> findAllPosts(User user) {
        return postRepository.findByUser(user);
    }


    @Override
    public List<PostOnPageDTO> getPosts(User user){ //TODO
        List<Post> posts = findAllPosts(user);
        List<PostOnPageDTO> postsOnPage = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        for (Post item : posts){
            String dataTime = item.getLocalDateTime().format(formatter);
            PostOnPageDTO postOnPageDTO = new PostOnPageDTO(item.getId(),item.getText(),dataTime,item.getComments(), item.getLikes(), item.getUser());
            postsOnPage.add(postOnPageDTO);
        }
        return postsOnPage;
    }

    @Override
    public Post findPost(long id) {
       Optional<Post> postFindById = postRepository.findById(id);
       Post post = new Post();
       if(postFindById.isPresent()){
         post = postFindById.get();
       } else throw new  NoSuchPostException();
       return post;
    }


    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void updatePost(long id, PostUpdateDTO postUpdateDTO) {
        Optional<Post> updatePost = postRepository.findById(id);
        if (updatePost.isPresent()) {
            Post post = updatePost.get();
            post.setText(postUpdateDTO.getTextPostUpdateDTO());
            postRepository.save(post);
        }
    }

    @Override
    public Post getPostByComment(Comment comment){
        return postRepository.findByComments(comment);
    }

    @Override
    public Post getPostById(long id){
        Optional<Post> post =postRepository.findById(id);
        Post postOnPage = new Post();
        if(post.isPresent()){
          return   postOnPage = post.get();
        } else  throw new NoSuchPostException();
    }


}
