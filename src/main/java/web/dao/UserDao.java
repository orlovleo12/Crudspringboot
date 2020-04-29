package web.dao;


import web.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User application);
    void deleteUser(Long userId);
    void updateUser(User application);
    List<User> getAllUsers();
    User getUserById(Long userId);
    User getUserByUsername(String username);
}
