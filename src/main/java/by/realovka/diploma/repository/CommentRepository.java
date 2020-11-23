package by.realovka.diploma.repository;

import by.realovka.diploma.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteById(Comment id);

}
