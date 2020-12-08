package by.realovka.diploma.service;

import by.realovka.diploma.entity.User;
import by.realovka.diploma.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepositoryMock;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUserById_Found_User() {

        when(userRepositoryMock.findById(1)).thenReturn(new User (1, "User", "USER"));
        User userFindById = userService.getUserById(1);
        assertEquals(1, userFindById.getId());
        assertEquals("User", userFindById.getUsername());
        assertEquals("USER", userFindById.getPassword());

    }
}