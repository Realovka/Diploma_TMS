package by.realovka.diploma.repository;

import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.Role;
import by.realovka.diploma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
    List<User> findAllByUsername(String user);
    User findById(long id);
    Optional<User> findByPosts(Post post);


}
