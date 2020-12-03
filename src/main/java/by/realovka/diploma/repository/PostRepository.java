package by.realovka.diploma.repository;

import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserId(long userId);
    Post findByComments(Comment comment);//TODO

}
