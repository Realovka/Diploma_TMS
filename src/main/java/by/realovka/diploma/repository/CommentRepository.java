package by.realovka.diploma.repository;

import by.realovka.diploma.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteByPostId(long id);
    List<Comment> findByPostId(long id);

}
