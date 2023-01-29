package ru.project.carwash.service;

import ru.project.carwash.entity.User;
import ru.project.carwash.entity.UserDTO;

public interface UserService {
    User findUserByName(String username);
    User findUserById(int id);
    UserDTO saveUser(User user);
// admin only
    UserDTO saveAdmin(User user);
    void deleteUser(int id);
}
