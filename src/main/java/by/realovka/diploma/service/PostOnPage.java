package by.realovka.diploma.service;

import by.realovka.diploma.dto.CommentOnPageDTO;
import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.repository.CommentRepository;
import by.realovka.diploma.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PostOnPage {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getListComments(Post post) {
        List<Comment> comments = commentRepository.findByPostId(post.getId());
        Collections.sort(comments);
        return comments;
    }

    public List<CommentOnPageDTO> getListCommentsOnPage(Comment comment, DateTimeFormatter formatter) {
        List<CommentOnPageDTO> commentsOnPage = new ArrayList<>();
            String dataTimeComment = comment.getLocalDateTime().format(formatter);
            CommentOnPageDTO commentOnPageDTO = new CommentOnPageDTO(comment.getId(), comment.getText(), dataTimeComment, comment.getPost(), comment.getUser());
            commentsOnPage.add(commentOnPageDTO);
        return commentsOnPage;
    }

    public long addViewToPost(long postId) {
        Post postUpdate = postRepository.findById(postId).get();
        postUpdate.setView(postUpdate.getView() + 1);
        postRepository.save(postUpdate);
        return postRepository.findById(postId).get().getView();
    }

}
