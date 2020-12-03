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
        Post postDB = postRepository.findById(postId)
                      .orElseThrow(NoSuchPostException::new);
        Comment comment = new Comment(commentAddDTO.getTextCommentAddDTO(), LocalDateTime.now(), postDB, user);
        commentRepository.save(comment);

    }

    @Override
    public void deleteCommentOnPersonPage(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment getComment(long id) {
        if (commentRepository.findById(id).isPresent()) {
            return commentRepository.findById(id).get();
        } else throw new NoSuchCommentException();
    }


    @Override
    public void updateComment(long id, CommentUpdateDTO commentUpdateDTO) {
        Optional<Comment> updateComment = commentRepository.findById(id);
        if (updateComment.isPresent()) {
            Comment comment = updateComment.get();
            comment.setText(commentUpdateDTO.getTextCommentUpdateDTO());
            commentRepository.save(comment);
        }
    }

    @Override
    public void deleteCommentsByPostId(long id){
        commentRepository.deleteByPostId(id);
    }
}
