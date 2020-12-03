package by.realovka.diploma.service;

import by.realovka.diploma.dto.*;
import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.Like;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.repository.CommentRepository;
import by.realovka.diploma.repository.LikeRepository;
import by.realovka.diploma.repository.PostRepository;
import by.realovka.diploma.service.exсeption.NoSuchPostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LikeRepository likeRepository;

    @Override
    public void addPost(User user, PostAddDTO postAddDTO) {
        Post post = new Post(postAddDTO.getTextPostAddDTO(), LocalDateTime.now(), user);
        postRepository.save(post);
    }

    @Override
    public List<Post> findAllPosts(long userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public long addViewToPost(long postId){
      Post postUpdate = postRepository.findById(postId).get();
      postUpdate.setView(postUpdate.getView()+1);
      postRepository.save(postUpdate);
      return postRepository.findById(postId).get().getView();
    }


    @Override
    public List<PostOnPageDTO> getPosts(long userId){ //TODO
        List<Post> posts = findAllPosts(userId);
        Collections.sort(posts);
        List<PostOnPageDTO> postsOnPage = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        List<CommentOnPageDTO> commentsOnPage = new ArrayList<>();
        List<Like> likes = new ArrayList<>();
        List<LikeOnPageDTO> likesOnPage = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        for (Post item : posts){
            String dataTimePost = item.getLocalDateTime().format(formatter);
            PostOnPageDTO postOnPageDTO = new PostOnPageDTO(item.getId(),item.getText(),dataTimePost, addViewToPost(item.getId()), item.getUser());
            comments = commentRepository.findByPostId(item.getId());
            Collections.sort(comments);
            for(Comment var : comments){
                String dataTimeComment = var.getLocalDateTime().format(formatter);
                CommentOnPageDTO commentOnPageDTO = new CommentOnPageDTO(var.getId(), var.getText(), dataTimeComment, var.getPost(), var.getUser());
                commentsOnPage.add(commentOnPageDTO);
            }
            postOnPageDTO.setComments(commentsOnPage);
            likes = likeRepository.findByPostId(item.getId());
            for(Like count : likes){
                LikeOnPageDTO likeOnPageDTO = new LikeOnPageDTO(count.getPost(),count.getUser());
                likesOnPage.add(likeOnPageDTO);
            }
            postOnPageDTO.setLikes(likesOnPage);
            postsOnPage.add(postOnPageDTO);
        }
        return postsOnPage;
    }


    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void updatePost(long id, PostUpdateDTO postUpdateDTO) {
        Post updatePost = postRepository.findById(id)
                   .orElseThrow(NoSuchPostException::new);
            updatePost.setText(postUpdateDTO.getTextPostUpdateDTO());
            postRepository.save(updatePost);

    }

    @Override
    public Post getPostByComment(Comment comment){
        return postRepository.findByComments(comment);
    }

    @Override
    public Post getPostById(long id){
       return postRepository.findById(id)
               .orElseThrow(NoSuchPostException::new);
    }


}
