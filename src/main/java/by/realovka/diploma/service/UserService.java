package by.realovka.diploma.service;

import by.realovka.diploma.dto.UserRegDTO;
import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.repository.UserRepository;
import by.realovka.diploma.service.exseption.NoSuchCommentException;
import by.realovka.diploma.service.exseption.SuchUserIsPresentAlreadyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByUsername(name);
    }


    public void registrationUser(UserRegDTO userRegDTO){
        User userFromDB = userRepository.findByUsername(userRegDTO.getNameRegDTO());
        if (userFromDB==null){
            User userAuth = new User();
            userAuth.setUsername(userRegDTO.getNameRegDTO());
            userAuth.setPassword(passwordEncoder.encode(userRegDTO.getPasswordRegDTO()));
            userAuth.setEmail(userRegDTO.getEmailRegDTO());
            userAuth.setPhone(userRegDTO.getPhoneRegDTO());
            userAuth.setRoles(Collections.singleton(userRegDTO.getRoleRegDTO()));
            userRepository.save(userAuth);
        } else throw new SuchUserIsPresentAlreadyException();

    }

    public List<User> getFriends(User user){
      return userRepository.findById(user.getId()).getFriends();
    }

    public List<User> getAllUsersWithoutAuthUser(User user){
        List<User> allUsers = userRepository.findAll(); //TODO
        allUsers.removeIf(user1 -> user1.getUsername().equals(user.getUsername())); //удаляем авторизированного юзера, чтобы он не мог сам себя добавить в друзья
        return allUsers;
    }

    public User getUserById(long id){
        return  userRepository.findById(id);
    }

    public void addPersonToFriend(long personId, User user){
        User personWhoNeedAddToFriend = userRepository.findById(personId);
        user.getFriends().add(personWhoNeedAddToFriend);
        userRepository.save(user);
        addAuthUserToFriend(user,personWhoNeedAddToFriend);
    }

    private void addAuthUserToFriend(User user,  User personWhoNeedAddToFriend){
        personWhoNeedAddToFriend.getFriends().add(user);
        userRepository.save(personWhoNeedAddToFriend);
    }


    public boolean getAnswerIsPersonUserFriend(User auth, User person){
        List<User> friends = auth.getFriends();
        for(User item : friends){
            if(item.getUsername().equals(person.getUsername())){
                return true;
            }
        }
        return  false;
    }

    public User getByUserName(String name){
      return   userRepository.findByUsername(name);
    }
}
