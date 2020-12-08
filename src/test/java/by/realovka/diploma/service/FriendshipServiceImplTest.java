package by.realovka.diploma.service;

import by.realovka.diploma.repository.FriendshipRepository;
import by.realovka.diploma.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class FriendshipServiceImplTest {

    private FriendshipServiceImpl friendshipService;
    private FriendshipRepository friendshipRepository;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
//        friendshipRepository = Mockito.mock(FriendshipRepository.class);
//        userRepository = Mockito.mock(UserRepository.class);
//        friendshipService = new FriendshipServiceImpl(friendshipRepository, userRepository);
    }


    @Test
    void getAnswerAreUserAndPersonFriends() {

    }
}