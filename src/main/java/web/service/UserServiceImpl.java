package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiсe/*, UserDetailsService*/ {

    @Autowired
    private UserDao userDao;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if (user.getPassword().equals("")) {
            user.setPassword(userDao.getUserByUsername(user.getUsername()).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.updateUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int userId) {
        return userDao.getUserById((long) userId);
    }

    /*@Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userCandidate = Optional.ofNullable(userDao.getUserByUsername(username));
        return userCandidate.orElseThrow(IllegalArgumentException::new);
    }*/
}
