package by.realovka.diploma.service;

import by.realovka.diploma.entity.User;

import java.util.List;

public interface FriendshipService {
    void saveFriendship(User user, User person);
    boolean getAnswerAreUserAndPersonFriends(User user, User person);
    List<User> getAllFriendsAuthUser(User user);
    List<User> getAllFriendsPerson(User user, User auth);
    List<User> getUsersWhoMayBeFriends(User auth);
}
