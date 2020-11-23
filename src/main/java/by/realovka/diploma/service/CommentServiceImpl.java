package by.realovka.diploma.service;

import by.realovka.diploma.dto.CommentAddDTO;
import by.realovka.diploma.dto.CommentUpdateDTO;
import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.repository.CommentRepository;
import by.realovka.diploma.repository.PostRepository;
import by.realovka.diploma.service.exсeption.NoSuchCommentException;
import by.realovka.diploma.service.exсeption.NoSuchPostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;


    @Override
    public void addComment(long postId, User user, CommentAddDTO commentAddDTO) {
        Optional<Post> post = postRepository.findById(postId);
        Post postCommentAdd = new Post();
        if(post.isPresent()){
            postCommentAdd = post.get();
        } else new NoSuchPostException();
        Comment comment = new Comment(commentAddDTO.getTextCommentAddDTO(), LocalDateTime.now(), postCommentAdd, user);
        commentRepository.save(comment);

    }

    @Override
    public void deleteCommentOnPersonPage(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment getComment(long commentId) {
        if (commentRepository.findById(commentId).isPresent()) {
            return commentRepository.findById(commentId).get();
        } else throw new NoSuchCommentException();
    }


    @Override
    public void updateComment(long commentId, CommentUpdateDTO commentUpdateDTO) {
        Optional<Comment> updateComment = commentRepository.findById(commentId);
        if (updateComment.isPresent()) {
            Comment comment = updateComment.get();
            comment.setText(commentUpdateDTO.getTextCommentUpdateDTO());
            commentRepository.save(comment);
        }
    }

}
