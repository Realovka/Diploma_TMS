package by.realovka.diploma.service;

import by.realovka.diploma.entity.User;

import java.util.List;

public interface FriendshipService {
    void saveFriendship(User user, User person);
    boolean getAnswerAreUserAndPersonFriends(long userId, long personId);
    List<User> getAllFriendsAuthUser(long userId);
    List<User> getAllFriendsPerson(long personId, long userId);
    List<User> getUsersWhoMayBeFriends(long userId);
}
