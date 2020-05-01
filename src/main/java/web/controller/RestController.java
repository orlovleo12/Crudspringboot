package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;
import web.service.UserServiceImpl;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {
    @Autowired
    public UserServiceImpl userService;

    @Autowired
    public UserDao userDao;

    @GetMapping(path = "list")
    public ResponseEntity<List<User>> listUserGetRestController() {
        List<User> listUsers = userService.getAllUsers();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @PutMapping(path = "update")
    public ResponseEntity<User> updateUserPutRestController(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path = "create")
    public ResponseEntity<User> createUserPostRestController(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "read/{s}")
    public ResponseEntity<User> readUserGetRestController(@PathVariable Long s) {
        return new ResponseEntity<User>(userDao.getUserById(s), HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<List<User>> deleteUserRestController(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "user/{s}")
    public ResponseEntity<User> userGetRestController(@PathVariable String s) {
        return new ResponseEntity<User>(userDao.getUserByUsername(s), HttpStatus.OK);
    }
}




