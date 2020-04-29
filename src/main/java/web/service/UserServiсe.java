package web.service;




import web.model.User;

import java.util.List;

public interface UserServiсe {
    void addUser(User user);

    void deleteUser(Long userId);

    void updateUser(User user);

    List<User> getAllUsers();

    User getUserById(int userId);


}
