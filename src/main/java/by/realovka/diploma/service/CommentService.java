package by.realovka.diploma.service;

import by.realovka.diploma.dto.CommentAddDTO;
import by.realovka.diploma.dto.CommentUpdateDTO;
import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.User;

public interface CommentService {
    void addComment(long postId, User user, CommentAddDTO commentAddDTO);
    void deleteCommentOnPersonPage(long id);
    Comment getComment(long commentId);
    void updateComment(long commentId, CommentUpdateDTO commentUpdateDTO);
}
