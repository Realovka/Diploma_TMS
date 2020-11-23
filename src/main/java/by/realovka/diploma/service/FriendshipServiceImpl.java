package by.realovka.diploma.service;

import by.realovka.diploma.entity.Friendship;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.repository.FriendshipRepository;
import by.realovka.diploma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveFriendship(User user, User person) {
        Friendship friendshipUserAndPerson = new Friendship(user, person);
        Friendship friendshipPersonAndUser = new Friendship(person, user);
        friendshipRepository.save(friendshipUserAndPerson);
        friendshipRepository.save(friendshipPersonAndUser);
    }

    @Override
    public boolean getAnswerAreUserAndPersonFriends(User user, User person) {
        List<Friendship> friendships = friendshipRepository.findAll();
        for (Friendship item : friendships) {
            if (item.getAuth().getId() == user.getId() && item.getPerson().getId() == person.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAllFriendsAuthUser(User user) {
        List<Friendship> friendships = friendshipRepository.findAll();
        List<User> friends = new ArrayList<>();
        for (Friendship item : friendships) {
            if (item.getAuth().getId() == user.getId()) {
                friends.add(item.getPerson());
            }
        }
        return friends;
    }

    @Override
    public List<User> getAllFriendsPerson(User user, User auth) {
        List<Friendship> friendships = friendshipRepository.findAll();
        List<User> friends = new ArrayList<>();
        for (Friendship item : friendships) {
            if (item.getAuth().getId() == user.getId()) {
                friends.add(item.getPerson());
            }
        }
        friends.removeIf(userFr -> userFr.getUsername().equals(auth.getUsername()));
        return friends;
    }


    @Override
    public List<User> getUsersWhoMayBeFriends(User auth) {
        List<User> allUsers = userRepository.findAll();
        List<User> friends = getAllFriendsAuthUser(auth);
        allUsers.removeAll(friends);
        allUsers.removeIf(user -> user.getUsername().equals(auth.getUsername()));
        return allUsers;

    }
}
