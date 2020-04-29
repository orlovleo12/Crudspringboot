package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleServiceImp;
import web.service.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {
    @Autowired
    public UserServiceImpl userService;
    @Autowired
    public RoleServiceImp roleService;

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

    @GetMapping(path = "read")
    public ResponseEntity<User> readUserGetRestController(HttpSession session) {
        return new ResponseEntity<>((User) session.getAttribute("user"), HttpStatus.OK);
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<List<User>> deleteUserRestController(@RequestBody Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}



