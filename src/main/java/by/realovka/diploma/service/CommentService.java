package by.realovka.diploma.service;

import by.realovka.diploma.dto.CommentAddDTO;
import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.repository.CommentRepository;
import by.realovka.diploma.service.exseption.NoSuchCommentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void addComment(long postId, String userWhoAddCommentName, String userWhoWritePostName, CommentAddDTO commentAddDTO) {
        Comment comment = new Comment(commentAddDTO.getTextCommentAddDTO(), new java.sql.Timestamp(System.currentTimeMillis()), postId,
                userWhoAddCommentName, userWhoWritePostName);
        commentRepository.save(comment);

    }

    public void deleteCommentOnPersonPage(long id){
        commentRepository.deleteById(id);
    }

    public Comment getComment(long commentId){
        Optional<Comment> comment=commentRepository.findById(commentId);
        if(comment.isPresent()){
            return  comment.get();
        }
        else throw new NoSuchCommentException();
    }


}
