package by.realovka.diploma.service;

import by.realovka.diploma.dto.UserRegDTO;
import by.realovka.diploma.entity.User;

public interface UserService {

    User getUserById(long id);
    void registrationUser(UserRegDTO userRegDTO);
    void setUserDeleted(User user);
    void setUserNotDeleted(String username);
}
