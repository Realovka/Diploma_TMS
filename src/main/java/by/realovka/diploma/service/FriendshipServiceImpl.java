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
    public boolean getAnswerAreUserAndPersonFriends(long userId, long personId) {
        List<Friendship> friendships = friendshipRepository.findAll();
        for (Friendship item : friendships) {
            if (item.getAuth().getId() == userId && item.getPerson().getId() == personId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAllFriendsAuthUser(long userId) {
        List<Friendship> friendships = friendshipRepository.findAll();
        List<User> friends = new ArrayList<>();
        for (Friendship item : friendships) {
            if (item.getAuth().getId() == userId) {
                friends.add(item.getPerson());
            }
        }
        return friends;
    }

    @Override
    public List<User> getAllFriendsPerson(long personId, long userId) {
        List<Friendship> friendships = friendshipRepository.findAll();
        List<User> friends = new ArrayList<>();
        for (Friendship item : friendships) {
            if (item.getAuth().getId() == personId) {
                friends.add(item.getPerson());
            }
        }
        friends.removeIf(userFr -> userFr.getId() == userId);
        return friends;
    }


    @Override
    public List<User> getUsersWhoMayBeFriends(long userId) {
        List<User> allUsers = userRepository.findAll();
        List<User> friends = getAllFriendsAuthUser(userId);
        allUsers.removeAll(friends);
        allUsers.removeIf(user -> user.getId()==userId);
        return allUsers;

    }
}
