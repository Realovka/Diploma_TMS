package by.realovka.diploma.service;

import by.realovka.diploma.entity.Friendship;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.repository.FriendshipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class FriendshipServiceImplTest {
    @Autowired
    private FriendshipService friendshipService;
    @MockBean
    private FriendshipRepository friendshipRepository;


    @Test
    void getAnswerAreUserAndPersonFriends() {
        boolean areFriendsFirstAndSecond = false;
        User first = new User();
        first.setId(1);
        User second = new User();
        second.setId(2);
        Friendship friendship = new Friendship(first,second);
        List<Friendship> friendships = new ArrayList<>();
        friendships.add(friendship);
        for (Friendship item : friendships){
            if(item.getAuth().getId()==first.getId() && item.getPerson().getId()==second.getId()){
                areFriendsFirstAndSecond = true;
            }
        }
        assertTrue(areFriendsFirstAndSecond);
    }
}