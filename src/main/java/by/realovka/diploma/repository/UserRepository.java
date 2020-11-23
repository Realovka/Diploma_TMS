package by.realovka.diploma.repository;

import by.realovka.diploma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
    List<User> findAllByUsername(String user);
    User findById(long id);
    List<User> findUsersById(long id);




}
